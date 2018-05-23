package server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static Logger logger;

    public static void main(String[] args) {
        logger = Logger.getRootLogger();
        SpringApplication.run(Main.class, args);
    }

    public static Logger getLogger() {
        return logger;
    }
}
