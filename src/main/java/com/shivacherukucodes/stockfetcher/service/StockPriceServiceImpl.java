package com.shivacherukucodes.stockfetcher.service;

import com.shivacherukucodes.stockfetcher.dto.StockPriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    private static final Logger logger = LoggerFactory.getLogger(StockPriceServiceImpl.class); // Use Impl class name for clarity

    @Value("${stock.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    @Cacheable(value = "stocks", key = "#symbol")
    public Double getStockPrice(String symbol) {
        String url = "https://api.twelvedata.com/price?symbol=" + symbol + "&apikey=" + apiKey;

        ResponseEntity<StockPriceResponse> response = restTemplate.getForEntity(url, StockPriceResponse.class);
        StockPriceResponse body = response.getBody();

        if (body == null || body.getPrice() == null || body.getPrice().trim().isEmpty()) {
            logger.error("❌ Invalid response received from API for symbol: {}", symbol);
            throw new RuntimeException("Invalid response from TwelveData API for symbol: " + symbol);
        }

        logger.info("📡 [{}] Fetched price from API for {}: {}", LocalDateTime.now(), symbol, body.getPrice());

        return Double.parseDouble(body.getPrice().trim());
    }
}
