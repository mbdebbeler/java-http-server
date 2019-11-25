import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

    @Test
    public void testHello() {
        HelloWorld test = new HelloWorld();
        String expected = "Welcome, Monica.";
        String actual = test.greeting();

        assertEquals(expected, actual);
    }

}