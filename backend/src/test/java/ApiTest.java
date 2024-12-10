import kong.unirest.HttpResponse;
import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiTest {

    @Test
    void testGetProductsSuccess() {
        // Mock the HttpResponse
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(200);
        when(mockResponse.getBody()).thenReturn("{\"products\":[{\"code\":\"123\",\"product_name\":\"Test Product\"}]}");

        // Mock the GetRequest
        GetRequest mockRequest = mock(GetRequest.class);
        when(mockRequest.queryString(anyString(), anyString())).thenReturn(mockRequest);
        when(mockRequest.header(anyString(), anyString())).thenReturn(mockRequest);
        when(mockRequest.asString()).thenReturn(mockResponse);

        // Use MockedStatic to mock Unirest static methods
        try (MockedStatic<Unirest> unirestMock = mockStatic(Unirest.class)) {
            unirestMock.when(() -> Unirest.get(anyString())).thenReturn(mockRequest);

            // Call the method under test
            String result = Api.getProducts();

            // Assert the result
            assertNotNull(result);
            assertEquals("{\"products\":[{\"code\":\"123\",\"product_name\":\"Test Product\"}]}", result);
        }
    }

    @Test
    void testGetProductsFailure() {
        // Mock the HttpResponse
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatus()).thenReturn(404);
        when(mockResponse.getStatusText()).thenReturn("Not Found");

        // Mock the GetRequest
        GetRequest mockRequest = mock(GetRequest.class);
        when(mockRequest.queryString(anyString(), anyString())).thenReturn(mockRequest);
        when(mockRequest.header(anyString(), anyString())).thenReturn(mockRequest);
        when(mockRequest.asString()).thenReturn(mockResponse);

        // Use MockedStatic to mock Unirest static methods
        try (MockedStatic<Unirest> unirestMock = mockStatic(Unirest.class)) {
            unirestMock.when(() -> Unirest.get(anyString())).thenReturn(mockRequest);

            // Expect the method to throw an exception
            RuntimeException exception = assertThrows(RuntimeException.class, Api::getProducts);

            // Assert the exception message
            assertTrue(exception.getMessage().contains("Failed to fetch products: Not Found"));
        }
    }
}
