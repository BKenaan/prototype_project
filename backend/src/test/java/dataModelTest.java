package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import main.java.DataModel;

public class DataModelTest {

    @Test
    public void testExample() {
        // Arrange
        DataModel.Cart cart = new DataModel.Cart("cart1", "host1", null, null, "Open", 0.0, null);
        
        // Act
        cart.setCartId("test");
        
        // Assert
        assertEquals("test", cart.getCartId());
    }
}