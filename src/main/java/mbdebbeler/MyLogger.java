package mbdebbeler;

import java.io.File;
import java.util.logging.*;



public class MyLogger {
    private final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    private static FileHandler fileHandler;

    public static void init(String location) {
        makeDirectory(location);
        try {
            fileHandler = new FileHandler(location + "/logger.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
    }

    public void logSomething(Level level, String message) {
        logger.log(level, message);
    }

    public static void makeDirectory(String location) {
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

}
