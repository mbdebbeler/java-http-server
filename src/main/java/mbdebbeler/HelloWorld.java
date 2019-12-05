package mbdebbeler;
import java.util.logging.Level;

public class HelloWorld {
    private static MyLogger mylogger;

    public static String greeting() {
        String welcome = "Welcome, Monica.";
        return welcome;
    }

    public static void main(String[] args) {
        System.out.println(greeting());

        mylogger = new MyLogger();
        mylogger.init("Logs");

        mylogger.logSomething(Level.INFO, "Everything is fine.");
        mylogger.logSomething(Level.WARNING, "Things are not fine.");
        mylogger.logSomething(Level.SEVERE, "ALL IS LOST.");
    }

}
