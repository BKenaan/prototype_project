import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DataModel;
import com.example.Helpers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HelperTest {

    private Helpers helpers;
    private String cartId;
    private DataModel.Item item;

    @BeforeEach
    public void setUp() {
        helpers = new Helpers();
        List<String> participants = Arrays.asList("user1", "user2");
        cartId = helpers.createSharedCart("host1", participants, new Date());
        item = new DataModel.Item("1234we", "product1", "2540", 10, 5.0);
    }

   
    @Test
    public void testViewCartDetails() {
        List<DataModel.Item> items = helpers.viewCartDetails(cartId);
        assertNotNull(items);
    }

   

    @Test
    public void testFinalizeOrder() {
        helpers.finalizeOrder(cartId);
        assertEquals("Finalized", helpers.getOrderStatus(cartId));
    }

   
}