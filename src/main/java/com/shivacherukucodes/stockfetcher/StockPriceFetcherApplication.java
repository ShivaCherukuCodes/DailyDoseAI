package com.shivacherukucodes.stockfetcher;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableCaching
public class StockPriceFetcherApplication {
    private static final Logger logger = LoggerFactory.getLogger(StockPriceFetcherApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(StockPriceFetcherApplication.class, args);
            LocalDateTime now = LocalDateTime.now();
            logger.info("<<<<<<<<<<<< StockPriceFetcherApplication started at "+ now + " successfully >>>>>>>>>>>>");
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
        logger.info("<<<<<<<<<<<< StockPriceFetcherApplication is shutting down >>>>>>>>>>>>");
    }
}
