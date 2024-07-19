package org.vadarod.currencyratechecker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.vadarod.currencyratechecker.config.AppConsts.DATE_PATTERN;

import org.vadarod.currencyratechecker.entities.CurrencyDto;
import org.vadarod.currencyratechecker.services.CurrencyService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/load-currency-rates/{ondate}")
    public ResponseEntity<String> loadCurrencyRates(@PathVariable("ondate") String date) {
        System.out.println("Date" + date);
        try {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
            boolean success = currencyService.loadCurrencyRates(dateTime.getYear(), dateTime.getMonth().getValue(), dateTime.getDayOfMonth());
            if (success) {
                return ResponseEntity.ok("Currency rates were successfully loaded for date: " + date);
            } else {
                return ResponseEntity.badRequest().body("Currency rates were not successfully loaded for date: " + date);
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Expected format is yyyy-MM-dd");
        }
    }

    @GetMapping("/get-currency-rate/{ondate}/{curId}")
    public ResponseEntity<CurrencyDto> getCurrencyRate(@PathVariable("ondate") LocalDate date,
                                                       @PathVariable("curId") int curId) {
        CurrencyDto currencyDto = currencyService.findCurrencyDtoRateByDateAndCurId(date, curId);
        if (currencyDto != null) {
            return ResponseEntity.ok(currencyDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
