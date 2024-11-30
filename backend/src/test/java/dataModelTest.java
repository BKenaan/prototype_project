package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DataModelTest {

    @Test
    public void testExample() {
        // Arrange
        DataModel dataModel = new DataModel();
        
        // Act
        dataModel.setValue("test");
        
        // Assert
        assertEquals("test", dataModel.getValue());
    }
}