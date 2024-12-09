import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataModelTest {

    private dataModel.Cart cart;
    private dataModel.Item item;

    @BeforeEach
    public void setUp() {
        List<String> participants = Arrays.asList("user1", "user2");
        cart = new dataModel.Cart("cart1", "host1", participants, new ArrayList<>(), "Open", 5.0, new Date());
        item = new dataModel.Item("1234we", "product1", "2540", 10, 5.0);
    }
    

    @Test
    public void testCartCreation() {
        assertEquals("cart1", cart.getCartId());
        assertEquals("host1", cart.getHostId());
        assertEquals(2, cart.getParticipants().size());
        assertEquals("Open", cart.getStatus());
    }

    @Test
    public void testAddItemToCart() {
        cart.getItems().add(item);
        assertEquals(1, cart.getItems().size());
        assertEquals("item1", cart.getItems().get(0).getProductId());
    }

    @Test
    public void testItemCreation() {
        assertEquals("item1", item.getProductId());
        assertEquals("product1", item.getProductId());
        assertEquals(2, item.getQuantity());
        assertEquals(10.0, item.getPrice());
    }

    @Test
    public void testCartStatusUpdate() {
        cart.setStatus("Finalized");
        assertEquals("Finalized", cart.getStatus());
    }
}