import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataModelTest {

    private DataModel.Cart cart;
    private DataModel.Item item;

    @BeforeEach
    public void setUp() {
        List<String> participants = Arrays.asList("user1", "user2");
        cart = new DataModel.Cart("cart1", "host1", participants, new ArrayList<>(), "Open", 5.0, new Date());
        item = new DataModel.Item("1234we", "product1", "2540", 10, 5.0);
    }
    
    @Test
    public void testCartCreation() {
        assertEquals("cart1", cart.getCartId());
        assertEquals("host1", cart.getHostId());
        assertEquals(2, cart.getParticipants().size());
        assertEquals("Open", cart.getStatus());
        assertEquals(5.0, cart.getGroupDeliveryFee());
        assertNotNull(cart.getDeadline());
    }


    @Test
    public void testCartStatusUpdate() {
        cart.setStatus("Finalized");
        assertEquals("Finalized", cart.getStatus());
    }

    @Test
    public void testAddItemToCart() {
        cart.addItem(item);
        assertEquals(1, cart.getItems().size());
        assertEquals(item, cart.getItems().get(0));
    }

    @Test
    public void testRemoveItemFromCart() {
        cart.addItem(item);
        assertTrue(cart.removeItem("1234we", "2540"));
        assertEquals(0, cart.getItems().size());
}

    @Test
    public void testRemoveNonExistentItem() {
        assertFalse(cart.removeItem("invalidId", "invalidUser"));
    }

    @Test
    public void testViewItemsInCart() {
        cart.addItem(item);
        List<DataModel.Item> items = cart.viewItems();
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testNullParticipants() {
        cart.setParticipants(null);
        assertNull(cart.getParticipants());
    }

    @Test
    public void testGroupDeliveryFeeUpdate() {
        cart.setGroupDeliveryFee(10.0);
        assertEquals(10.0, cart.getGroupDeliveryFee());
    }

    @Test
    public void testDeadlineUpdate() {
        Date newDeadline = new Date(System.currentTimeMillis() + 3600 * 1000);
        cart.setDeadline(newDeadline);
        assertEquals(newDeadline, cart.getDeadline());
    }

    @Test
    public void testItemAttributes() {
        assertEquals("1234we", item.getProductId());
        assertEquals("product1", item.getName());
        assertEquals("2540", item.getUserId());
        assertEquals(10, item.getQuantity());
        assertEquals(5.0, item.getPrice());
    }

    @Test
    public void testRemoveItemWithNullItems() {
        cart.setItems(null);
        assertFalse(cart.removeItem("1234we", "2540"));
    }

}
