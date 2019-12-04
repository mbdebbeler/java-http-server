package mbdebbeler;

import mbdebbeler.MyLogger;

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

        mylogger.log("message 1");
        mylogger.log("message 2");
        mylogger.log("message 3");
    }

}
