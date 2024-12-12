import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DataModel;
import com.example.Helpers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainTest {

    private Helpers helpers;
    private String cartId;
    private DataModel.Item milk;
    private DataModel.Item bread;
    private DataModel.Item eggs;

    @BeforeEach
    public void setUp() {
        helpers = new Helpers();
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 3600 * 1000);
        cartId = helpers.createSharedCart("host123", participants, deadline);

        milk = new DataModel.Item("item1", "Milk", "user1", 2, 1.5);
        bread = new DataModel.Item("item2", "Bread", "user2", 1, 0.8);
        eggs = new DataModel.Item("item3", "Eggs", "user3", 12, 0.1);
    }

    @Test
    public void testViewCartDetails() {
        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);
        List<DataModel.Item> items = helpers.viewCartDetails(cartId);
        assertNotNull(items);
        assertEquals(3, items.size());
    }

   
    
}
