package server;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    private final static Logger logger = Logger.getLogger(ServerLogger.class.getName());
    private static FileHandler fileHandler;

    public ServerLogger() {
        String location = "Logs";
        String fileName = "logger.log";
        makeDirectory(location);
        makeFile(fileName);
    }

    public FileHandler makeFileHandler() {
        try {
            fileHandler = new FileHandler("Logs/logger.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileHandler;
    }

    public void logSomething(Level level, String message) {
        logger.setUseParentHandlers(false);
        FileHandler fileHandler = makeFileHandler();
        logger.addHandler(fileHandler);
        logger.log(level, message);
        fileHandler.close();
    }

    private static void makeDirectory(String location) {
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static void makeFile(String fileName) {
        File logFile = new File(fileName);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
