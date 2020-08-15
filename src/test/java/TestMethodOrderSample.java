import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.trim;

// TestMethodOrderer comes only after junit jupiter engine 5.4.1 version
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMethodOrderSample {
    @Test
    @Order(1)
    public void testMethodOrder1() {
        System.out.println("1");
    }

    @Test
    @Order(2)
    public void testMethodOrder2() {
        System.out.println("2");
    }

    @Test
    @Order(3)
    public void testMethodOrder3() {
        System.out.println("3");
    }
}
