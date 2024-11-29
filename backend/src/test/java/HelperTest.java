package com;

import com.datamodel.Cart;
import com.datamodel.Item;
import com.datamodel.SavingsSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    private Helpers helpers;

    @BeforeEach
    void setUp() {
        helpers = new Helpers();
    }

    @Test
    void testCreateSharedCart() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        assertNotNull(cartId, "Cart ID should not be null");
        assertDoesNotThrow(() -> {
            helpers.viewCartDetails(cartId);
        }, "Cart should be retrievable");
    }

    @Test
    void testAddItemToCart() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        Item item = new Item("item1", "product1", "user1", 2, 15.0);
        helpers.addItemToCart(cartId, "user1", item);

        List<Item> items = helpers.viewCartDetails(cartId);
        assertEquals(1, items.size(), "Cart should contain one item");
        assertEquals(item, items.get(0), "Added item should match");
    }

    @Test
    void testRemoveItemFromCart() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        Item item = new Item("item1", "product1", "user1", 2, 15.0);
        helpers.addItemToCart(cartId, "user1", item);

        helpers.removeItemFromCart(cartId, "item1", "user1");
        List<Item> items = helpers.viewCartDetails(cartId);
        assertTrue(items.isEmpty(), "Cart should be empty after item removal");
    }

    @Test
    void testCalculateTotalCost() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        helpers.addItemToCart(cartId, "user1", new Item("item1", "product1", "user1", 2, 10.0));
        helpers.addItemToCart(cartId, "user2", new Item("item2", "product2", "user2", 3, 20.0));

        double totalCost = helpers.calculateTotalCost(cartId);
        assertEquals(80.0, totalCost, "Total cost should be 80.0");
    }

    @Test
    void testFinalizeOrder() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        helpers.finalizeOrder(cartId);
        assertEquals("Finalized", helpers.getOrderStatus(cartId), "Cart should be finalized");
    }

    @Test
    void testCheckAndAutoFinalizeOrder() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() - 1000); // Deadline passed

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        helpers.checkAndAutoFinalizeOrder(cartId, new Date());
        assertEquals("Finalized", helpers.getOrderStatus(cartId), "Order should be auto-finalized after the deadline");
    }

    @Test
    void testCalculateSavings() {
        double individualDeliveryFee = 10.0;
        double groupDeliveryFee = 25.0;
        int participants = 3;

        double savings = helpers.calculateSavings(individualDeliveryFee, groupDeliveryFee, participants);
        assertEquals(5.0, savings, "Total savings should be 5.0");
    }

    @Test
    void testGetSavingsDetails() {
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 10000);

        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        double individualDeliveryFee = 10.0;
        double groupDeliveryFee = 25.0;

        SavingsSummary summary = helpers.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee);

        assertEquals(5.0, summary.getTotalSavings(), "Total savings should be 5.0");
        assertEquals(1.67, Math.round(summary.getIndividualSavings() * 100.0) / 100.0, "Individual savings should be approximately 1.67");
    }
}
