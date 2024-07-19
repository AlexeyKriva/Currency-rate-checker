package org.vadarod.currencyratechecker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vadarod.currencyratechecker.entities.Currency;
import org.vadarod.currencyratechecker.services.CurrencyService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;
    @PostMapping("/load-currency-rates/{ondate}")
    public ResponseEntity<String> loadCurrencyRates(@PathVariable("ondate") LocalDate date) {
        boolean success = currencyService.loadCurrencyRates(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        if (success) {
            return ResponseEntity.ok("Currency rates were successfully loaded for date: " + date);
        } else {
            return ResponseEntity.badRequest().body("Currency rates were not successfully loaded for date: " + date);
        }
    }
}
