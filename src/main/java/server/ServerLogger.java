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
        String fileName = "logger.log";
        makeFile(fileName);
    }

    public FileHandler makeFileHandler() {
        try {
            fileHandler = new FileHandler("logger.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileHandler;
    }

    public void logSomething(Level level, String message) {
        logger.setUseParentHandlers(false);
        try {
            fileHandler = makeFileHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        if (fileHandler == null) {
            return;
        }
        logger.addHandler(fileHandler);
        try {
            logger.log(level, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        fileHandler.close();
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
