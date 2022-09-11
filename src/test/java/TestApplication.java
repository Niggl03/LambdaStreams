import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    private Application application;

    @BeforeEach
    public void setup() {
        application = new Application();
        application.loadRecords();
    }

    @Test
    @Order(1)
    public void executeSQL01() {
        assertEquals(1000000, application.executeQuery01());
    }

    @Test
    @Order(3)
    public void executeSQL03() {
        assertEquals(20414, application.executeQuery03());
    }

    @Test
    @Order(5)
    public void executeSQL05() {
        assertEquals(507, application.executeQuery05());
    }
    @Test
    @Order(10)
    public void executeSQL10() {
        Map<String, Long> data = application.executeQuery10();
        assertEquals(3454, data.get("W"));
        assertEquals(3519, data.get("D"));
        assertEquals(3540, data.get("S"));
        assertEquals(3406, data.get("Y"));
        assertEquals(3517, data.get("M"));
    }

}
