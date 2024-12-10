import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class DataModelTest {

    private DataModel.Cart cart;
    private DataModel.Item item1;
    private DataModel.Item item2;

    @BeforeEach
    public void setUp() {
        List<DataModel.Item> items = new ArrayList<>();
        item1 = new DataModel.Item("1", "Item1", "User1", 1, 10.0);
        item2 = new DataModel.Item("2", "Item2", "User2", 2, 20.0);
        items.add(item1);
        items.add(item2);

        cart = new DataModel.Cart("cart1", "host1", new ArrayList<>(), items, "Open", 5.0, new Date(System.currentTimeMillis() + 3600 * 1000));
    }

    @Test
    public void testRemoveItem_Success() {
        boolean result = cart.removeItem("1", "User1");
        assertTrue(result);
        assertEquals(1, cart.getItems().size());
        assertFalse(cart.getItems().contains(item1));
    }

    @Test
    public void testRemoveItem_Failure_ItemNotFound() {
        boolean result = cart.removeItem("3", "User1");
        assertFalse(result);
        assertEquals(2, cart.getItems().size());
    }

    @Test
    public void testRemoveItem_Failure_UserMismatch() {
        boolean result = cart.removeItem("1", "User2");
        assertFalse(result);
        assertEquals(2, cart.getItems().size());
    }

    @Test
    public void testRemoveItem_Failure_NullItems() {
        cart.setItems(null);
        boolean result = cart.removeItem("1", "User1");
        assertFalse(result);
    }
}