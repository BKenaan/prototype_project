package com;

import com.datamodel.Item;
import com.datamodel.SavingsSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Helpers helpers;

    @BeforeEach
    void setUp() {
        helpers = new Helpers();
    }

    @Test
    void testMainWorkflow() {
        // Setup: Create a shared cart
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 60000); // 1 minute from now

        String cartId = helpers.createSharedCart(hostId, participants, deadline);
        assertNotNull(cartId, "Cart ID should not be null");

        // Step 1: Add items to the cart
        Item item1 = new Item("item1", "product1", "user1", 2, 15.0); // 2 units, $15 each
        Item item2 = new Item("item2", "product2", "user2", 1, 30.0); // 1 unit, $30

        helpers.addItemToCart(cartId, "user1", item1);
        helpers.addItemToCart(cartId, "user2", item2);

        // Verify: Items added
        List<Item> items = helpers.viewCartDetails(cartId);
        assertEquals(2, items.size(), "Cart should contain two items");
        assertEquals(item1, items.get(0), "First item should match");
        assertEquals(item2, items.get(1), "Second item should match");

        // Step 2: Calculate total cost
        double totalCost = helpers.calculateTotalCost(cartId);
        assertEquals(60.0, totalCost, "Total cart cost should be $60.0");

        // Step 3: Finalize the cart manually
        helpers.finalizeOrder(cartId);
        assertEquals("Finalized", helpers.getOrderStatus(cartId), "Cart status should be finalized");

        // Step 4: Calculate savings
        double individualDeliveryFee = 10.0; // Assume $10 delivery fee per person
        double groupDeliveryFee = 25.0; // Group delivery fee is $25

        SavingsSummary savingsSummary = helpers.getSavingsDetails(cartId, individualDeliveryFee, groupDeliveryFee);

        assertEquals(5.0, savingsSummary.getTotalSavings(), "Total savings should be $5.0");
        assertEquals(1.67, Math.round(savingsSummary.getIndividualSavings() * 100.0) / 100.0, "Individual savings should be approximately $1.67");
    }

    @Test
    void testDeadlineAutoFinalize() {
        // Setup: Create a cart with a passed deadline
        String hostId = "host123";
        List<String> participants = Arrays.asList("user1", "user2");
        Date pastDeadline = new Date(System.currentTimeMillis() - 1000); // Deadline already passed

        String cartId = helpers.createSharedCart(hostId, participants, pastDeadline);

        // Action: Check and auto-finalize order
        helpers.checkAndAutoFinalizeOrder(cartId, new Date());

        // Verify: Cart should be auto-finalized
        assertEquals("Finalized", helpers.getOrderStatus(cartId), "Cart should be auto-finalized after the deadline");
    }
}
