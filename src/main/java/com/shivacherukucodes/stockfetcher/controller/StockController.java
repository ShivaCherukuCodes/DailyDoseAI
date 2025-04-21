package com.shivacherukucodes.stockfetcher.controller;

import com.shivacherukucodes.stockfetcher.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getStockPrice(@PathVariable String symbol) {
        try {
            Double price = stockPriceService.getStockPrice(symbol);
            return ResponseEntity.ok(price);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching stock price: " + e.getMessage());
        }
    }
}
