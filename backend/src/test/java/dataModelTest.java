package test.java;

import com.datamodel.Cart;
import com.datamodel.Item;
import com.datamodel.Product;
import com.datamodel.SavingsSummary;
import com.datamodel.Student;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class datamodelTest {

    @Test
    void testCartCreation() {
        String cartId = "cart123";
        String hostId = "host123";
        Date deadline = new Date();
        Cart cart = new Cart(cartId, hostId, Arrays.asList("user1", "user2"), null, "Open", 10.0, deadline);

        assertEquals(cartId, cart.getCartId(), "Cart ID should match");
        assertEquals(hostId, cart.getHostId(), "Host ID should match");
        assertEquals("Open", cart.getStatus(), "Cart status should match");
        assertEquals(10.0, cart.getGroupDeliveryFee(), "Delivery fee should match");
        assertEquals(deadline, cart.getDeadline(), "Deadline should match");
    }

    @Test
    void testAddAndRemoveItemInCart() {
        Cart cart = new Cart("cart123", "host123", Arrays.asList("user1"), null, "Open", 5.0, new Date());

        Item item = new Item("item1", "product1", "user1", 2, 15.0);
        cart.addItem(item);

        assertEquals(1, cart.getItems().size(), "Cart should contain one item");
        assertEquals(item, cart.getItems().get(0), "Item should match");

        boolean removed = cart.removeItem("item1", "user1");
        assertTrue(removed, "Item should be removed successfully");
        assertTrue(cart.getItems().isEmpty(), "Cart should be empty after removal");
    }

    @Test
    void testItemCreation() {
        Item item = new Item("item1", "product1", "user1", 2, 10.0);

        assertEquals("item1", item.getItemId(), "Item ID should match");
        assertEquals("product1", item.getProductId(), "Product ID should match");
        assertEquals("user1", item.getUserId(), "User ID should match");
        assertEquals(2, item.getQuantity(), "Quantity should match");
        assertEquals(10.0, item.getPrice(), "Price should match");
    }

    @Test
    void testProductCreation() {
        Product product = new Product("prod1", "Product Name", 20.0, 10);

        assertEquals("prod1", product.getProductId(), "Product ID should match");
        assertEquals("Product Name", product.getName(), "Product name should match");
        assertEquals(20.0, product.getPrice(), "Product price should match");
        assertEquals(10, product.getStockQuantity(), "Product stock quantity should match");
    }

    @Test
    void testSavingsSummaryCreation() {
        SavingsSummary savingsSummary = new SavingsSummary(50.0, 10.0, 25.0, 5);

        assertEquals(50.0, savingsSummary.getTotalSavings(), "Total savings should match");
        assertEquals(10.0, savingsSummary.getIndividualSavings(), "Individual savings should match");
        assertEquals(25.0, savingsSummary.getTotalDeliveryFee(), "Total delivery fee should match");
        assertEquals(5, savingsSummary.getNumberOfParticipants(), "Number of participants should match");
    }

    @Test
    void testStudentCreation() {
        Student student = new Student("student1", "password123");

        assertEquals("student1", student.getStudentId(), "Student ID should match");
        assertEquals("password123", student.getPassword(), "Password should match");
    }
}
