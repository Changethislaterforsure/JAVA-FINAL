package util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            LogManager.getLogManager().reset();
            FileHandler fh = new FileHandler("resources/logger.txt", true); 
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            System.err.println("Could not set up logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}