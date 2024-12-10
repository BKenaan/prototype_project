import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class HelpersTest {
    private Helpers helpers;

    @BeforeEach
    void setUp() {
        helpers = new Helpers();
    }

    @Test
    void testCreateSharedCart() {
        // Arrange
        String hostId = "host123";
        List<String> participants = List.of("user1", "user2");
        Date deadline = new Date(System.currentTimeMillis() + 3600000); // 1 hour later

        // Act
        String cartId = helpers.createSharedCart(hostId, participants, deadline);

        // Assert
        assertNotNull(cartId);
        assertEquals("cart1", cartId);
    }

    @Test
    void testAddItemToCart() {
        // Arrange
        String cartId = helpers.createSharedCart("host123", List.of("user1"), new Date());
        DataModel.Item item = new DataModel.Item("item1", "Milk", "user1", 2, 2.5);

        // Act
        helpers.addItemToCart(cartId, "user1", item);
        List<DataModel.Item> items = helpers.viewCartDetails(cartId);

        // Assert
        assertEquals(1, items.size());
        assertEquals("Milk", items.get(0).getName());
    }

    @Test
    void testCalculateTotalCost() {
        // Arrange
        String cartId = helpers.createSharedCart("host123", List.of("user1"), new Date());
        helpers.addItemToCart(cartId, "user1", new DataModel.Item("item1", "Milk", "user1", 2, 2.5));
        helpers.addItemToCart(cartId, "user1", new DataModel.Item("item2", "Bread", "user1", 1, 1.5));

        // Act
        double totalCost = helpers.calculateTotalCost(cartId);

        // Assert
        assertEquals(6.5, totalCost, 0.01);
    }

    @Test
    void testCalculateDeliveryFeeSplit() {
        // Act
        double splitFee = helpers.calculateDeliveryFeeSplit(5.0, 2);

        // Assert
        assertEquals(2.5, splitFee, 0.01);
    }
}
