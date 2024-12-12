import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Aire.CreateCartRequest;
import com.Aire.DataModel;
import com.Aire.Helpers;
import com.Aire.HelpersController;

import java.util.*;

public class HelpersControllerTest {
        @Autowired
    private MockMvc mockMvc;

    @Mock
    private Helpers helpers;

    @InjectMocks
    private HelpersController helpersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSharedCart() throws Exception {
        CreateCartRequest request = new CreateCartRequest();
        request.setHostId("host1");
        request.setParticipants(Arrays.asList("user1", "user2"));
        request.setDeadline(new Date());

        when(helpers.createSharedCart(anyString(), anyList(), any(Date.class))).thenReturn("cart123");

        mockMvc.perform(post("/api/helpers/create-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "hostId": "host1",
                        "participants": ["user1", "user2"],
                        "deadline": "2024-12-31T23:59:59.000+00:00"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value("cart123"));
    }

    @Test
    public void testAddItemToCart() throws Exception {
        doNothing().when(helpers).addItemToCart(anyString(), anyString(), any(DataModel.Item.class));

        mockMvc.perform(post("/api/helpers/add-item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "cartId": "cart123",
                        "userId": "user1",
                        "item": {
                            "productId": "prod123",
                            "name": "Product Name",
                            "userId": "user1",
                            "quantity": 2,
                            "price": 10.0
                        }
                    }
                """))
                .andExpect(status().isOk());
    }

    @Test
    public void testViewCartDetails() throws Exception {
        List<DataModel.Item> items = List.of(new DataModel.Item("prod123", "Product Name", "user1", 2, 10.0));
        when(helpers.viewCartDetails("cart123")).thenReturn(items);

        mockMvc.perform(get("/api/helpers/view-cart/cart123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("prod123"))
                .andExpect(jsonPath("$[0].name").value("Product Name"))
                .andExpect(jsonPath("$[0].quantity").value(2));
    }

    @Test
    public void testCalculateTotalCost() throws Exception {
        when(helpers.calculateTotalCost("cart123")).thenReturn(100.0);

        mockMvc.perform(get("/api/helpers/calculate-total/cart123"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testGetOrderStatus() throws Exception {
        when(helpers.getOrderStatus("cart123")).thenReturn("Finalized");

        mockMvc.perform(get("/api/helpers/order-status/cart123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Finalized"));
    }

    @Test
    public void testGetSavingsDetails() throws Exception {
        DataModel.SavingsSummary summary = new DataModel.SavingsSummary(20.0, 10.0, 5.0, 2);
        when(helpers.getSavingsDetails("cart123", 5.0, 10.0)).thenReturn(summary);

        mockMvc.perform(get("/api/helpers/savings-details/cart123")
                .param("individualDeliveryFee", "5.0")
                .param("groupDeliveryFee", "10.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalSavings").value(20.0))
                .andExpect(jsonPath("$.individualSavings").value(10.0))
                .andExpect(jsonPath("$.totalDeliveryFee").value(5.0))
                .andExpect(jsonPath("$.numberOfParticipants").value(2));
    }

    @Test
    public void testFetchProductsFromApi() throws Exception {
        List<DataModel.Product> products = List.of(new DataModel.Product("prod123", "Product Name", 10.0, 50));
        when(helpers.fetchProductsFromApi("query")).thenReturn(products);

        mockMvc.perform(get("/api/helpers/fetch-products")
                .param("query", "query"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("prod123"))
                .andExpect(jsonPath("$[0].name").value("Product Name"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[0].stockQuantity").value(50));
    }
}
