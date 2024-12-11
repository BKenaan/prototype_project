import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class ApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDataEndpoint() throws Exception {
        mockMvc.perform(get("/api/data"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello from the backend!"));
    }
}
