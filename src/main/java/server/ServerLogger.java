package server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    private final static Logger logger = Logger.getLogger(ServerLogger.class.getName());
    private static FileHandler fileHandler;

    public ServerLogger() {
        String location = "Logs";
        String nowDateTime = calculateDate();
        makeDirectory(location);
        try {
            fileHandler = new FileHandler(location + "/" + nowDateTime, 50000, 20, true);
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

    private static void makeDirectory(String location) {
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static String calculateDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MMMMM.dd'at'kk:mm");
        return dateFormat.format(date);
    }

}
