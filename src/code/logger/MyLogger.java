package code.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    private static final Logger logger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);


    public static void setupLogger(){

        LogManager.getLogManager().reset();
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new MyFormatter());
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);

        try{
            FileHandler fh = new FileHandler("logger.log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.INFO);
            logger.addHandler(fh);

        }catch(IOException e){
            logger.log(Level.SEVERE, "Помилка створення файлу логера", e);
        }

    }

    static class MyFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {

            return "Error: " + record.getMessage() + "\n";
        }

    }
}
