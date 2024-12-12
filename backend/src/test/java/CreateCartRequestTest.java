import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.CreateCartRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateCartRequestTest {
    private CreateCartRequest createCartRequest;

    @BeforeEach
    public void setUp() {
        createCartRequest = new CreateCartRequest();
    }

    @Test
    public void testHostId() {
        createCartRequest.setHostId("host123");
        assertEquals("host123", createCartRequest.getHostId());
    }

    @Test
    public void testParticipants() {
        List<String> participants = Arrays.asList("user1", "user2", "user3");
        createCartRequest.setParticipants(participants);
        assertEquals(participants, createCartRequest.getParticipants());
    }

    @Test
    public void testDeadline() {
        Date deadline = new Date();
        createCartRequest.setDeadline(deadline);
        assertEquals(deadline, createCartRequest.getDeadline());
    }
}
