import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HelperTest {

    private Helpers helpers;
    private String cartId;
    private dataModel.Item item;

    @BeforeEach
    public void setUp() {
        helpers = new Helpers();
        List<String> participants = Arrays.asList("user1", "user2");
        cartId = helpers.createSharedCart("host1", participants, new Date());
        item = new dataModel.Item("1234we", "product1", "2540", 10, 5.0);
    }

    @Test
    public void testCreateSharedCart() {
        assertNotNull(cartId);
        assertEquals(1, helpers.viewCartDetails(cartId).size());
    }

    @Test
    public void testAddItemToCart() {
        helpers.addItemToCart(cartId, "user1", item);
        List<dataModel.Item> items = helpers.viewCartDetails(cartId);
        assertEquals(1, items.size());
        assertEquals("item1", items.get(0).getProductId());
    }

    @Test
    public void testViewCartDetails() {
        List<dataModel.Item> items = helpers.viewCartDetails(cartId);
        assertNotNull(items);
    }

    @Test
    public void testCalculateTotalCost() {
        helpers.addItemToCart(cartId, "user1", item);
        double totalCost = helpers.calculateTotalCost(cartId);
        assertEquals(20.0, totalCost);
    }

    @Test
    public void testFinalizeOrder() {
        helpers.finalizeOrder(cartId);
        assertEquals("Finalized", helpers.getOrderStatus(cartId));
    }

    @Test
    public void testCheckAndAutoFinalizeOrder() {
        Date pastDate = new Date(System.currentTimeMillis() - 10000); // 10 seconds in the past
        helpers.checkAndAutoFinalizeOrder(cartId, pastDate);
        assertEquals("Finalized", helpers.getOrderStatus(cartId));
    }
}