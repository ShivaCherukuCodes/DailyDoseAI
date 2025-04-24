package com.dailydoseai;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableCaching
public class DailyDoseAIApplication {
    private static final Logger logger = LoggerFactory.getLogger(DailyDoseAIApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(DailyDoseAIApplication.class, args);
            LocalDateTime now = LocalDateTime.now();
            logger.info("<<<<<<<<<<<< DailyDoseAIApplication started at "+ now + " successfully >>>>>>>>>>>>");
            if (args.length > 0) {
                logger.info("Application started with arguments:");
                for (String arg : args) {
                    logger.info("ARG: {}", arg);
                }
            }
        } catch (Exception e) {
            logger.error("Application failed to start: {}", e.getMessage(), e);
        }
    }

    @PreDestroy
    public void onShutdown() {
        logger.info("<<<<<<<<<<<< DailyDoseAIApplication is shutting down >>>>>>>>>>>>");
    }
}
