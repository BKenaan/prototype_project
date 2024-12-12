import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Aire.DataModel;
import com.Aire.Helpers;

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

    @Test
    public void testCreateSharedCart() {
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        String newCartId = helpers.createSharedCart("host2", participants, new Date());
        assertNotNull(newCartId);
        assertEquals(2, helpers.getCarts().size()); 
    }

    @Test
    public void testAddItemToCart() {
        helpers.addItemToCart(cartId, "user1", item);
        List<DataModel.Item> items = helpers.viewCartDetails(cartId);
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testAddItemToNonExistentCart() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            helpers.addItemToCart("invalidCartId", "user1", item);
        });
        assertEquals("Cart with ID invalidCartId does not exist.", exception.getMessage());
    }

    @Test
    public void testCalculateTotalCost() {
        helpers.addItemToCart(cartId, "user1", item);
        double totalCost = helpers.calculateTotalCost(cartId);
        assertEquals(50.0, totalCost, 0.01);
    }

    @Test
    public void testCalculateIndividualCosts() {
        helpers.addItemToCart(cartId, "user1", item); // 50.0 for user1
        DataModel.Item item2 = new DataModel.Item("5678xy", "product2", "user2", 5, 10.0); // 50.0 for user2
        helpers.addItemToCart(cartId, "user2", item2);

        double deliveryFeeSplit = 5.0 / 2; // 2 participants

        var individualCosts = helpers.calculateIndividualCosts(cartId);
        assertEquals(50.0 + deliveryFeeSplit, individualCosts.get("user1"), 0.01);
        assertEquals(50.0 + deliveryFeeSplit, individualCosts.get("user2"), 0.01);
    }

    @Test
    public void testCalculateDeliveryFeeSplit() {
        double split = helpers.calculateDeliveryFeeSplit(10.0, 4); 
        assertEquals(2.5, split, 0.01);
    }

    @Test
    public void testCalculateSavings() {
        double savings = helpers.calculateSavings(5.0, 10.0, 4); 
        assertEquals(10.0, savings, 0.01);
    }

    @Test
    public void testGetSavingsDetails() {
        double individualFee = 5.0;
        double groupFee = 10.0;

        var summary = helpers.getSavingsDetails(cartId, individualFee, groupFee);

        assertEquals(10.0, summary.getTotalSavings(), 0.01);
        assertEquals(5.0, summary.getIndividualSavings(), 0.01);
        assertEquals(10.0, summary.getTotalDeliveryFee(), 0.01);
        assertEquals(2, summary.getNumberOfParticipants());
    }

    @Test
    public void testCheckAndAutoFinalizeOrder() {
        Date pastDeadline = new Date(System.currentTimeMillis() - 3600 * 1000); 
        helpers.createSharedCart("host3", Arrays.asList("user3", "user4"), pastDeadline);

        helpers.checkAndAutoFinalizeOrder(cartId, new Date());
        assertEquals("Finalized", helpers.getOrderStatus(cartId));
    }

    @Test
    public void testValidateCartContents() {
        helpers.addItemToCart(cartId, "user1", item);
        assertTrue(helpers.validateCartContents(cartId));
    }

   
}