import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainTest {

    private Helpers helpers;
    private String cartId;
    private dataModel.Item milk;
    private dataModel.Item bread;
    private dataModel.Item eggs;

    @BeforeEach
    public void setUp() {
        helpers = new Helpers();
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        Date deadline = new Date(System.currentTimeMillis() + 3600 * 1000); // 1 hour from now
        cartId = helpers.createSharedCart("host123", participants, deadline);

        milk = new dataModel.Item("item1", "Milk", "user1", 2, 1.5);
        bread = new dataModel.Item("item2", "Bread", "user2", 1, 0.8);
        eggs = new dataModel.Item("item3", "Eggs", "user3", 12, 0.1);
    }

    @Test
    public void testCreateSharedCart() {
        assertNotNull(cartId);
        assertEquals(3, helpers.viewCartDetails(cartId).size());
    }

    @Test
    public void testAddItemToCart() {
        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);
        List<dataModel.Item> items = helpers.viewCartDetails(cartId);
        assertEquals(3, items.size());
        assertEquals("Milk", items.get(0).getProductId());
        assertEquals("Bread", items.get(1).getProductId());
        assertEquals("Eggs", items.get(2).getProductId());
    }

    @Test
    public void testViewCartDetails() {
        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);
        List<dataModel.Item> items = helpers.viewCartDetails(cartId);
        assertNotNull(items);
        assertEquals(3, items.size());
    }

    @Test
    public void testCalculateTotalCost() {
        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);
        double totalCost = helpers.calculateTotalCost(cartId);
        assertEquals(5.3, totalCost);
    }

    @Test
    public void testCalculateIndividualCosts() {
        helpers.addItemToCart(cartId, "user1", milk);
        helpers.addItemToCart(cartId, "user2", bread);
        helpers.addItemToCart(cartId, "user3", eggs);
        Map<String, Double> individualCosts = helpers.calculateIndividualCosts(cartId);
        assertEquals(2 * 1.5, individualCosts.get("user1"));
        assertEquals(1 * 0.8, individualCosts.get("user2"));
        assertEquals(12 * 0.1, individualCosts.get("user3"));
    }
}