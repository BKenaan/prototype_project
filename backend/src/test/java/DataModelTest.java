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
    }



   

    @Test
    public void testCartStatusUpdate() {
        cart.setStatus("Finalized");
        assertEquals("Finalized", cart.getStatus());
    }
}
