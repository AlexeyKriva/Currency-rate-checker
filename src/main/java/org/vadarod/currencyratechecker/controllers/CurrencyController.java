package org.vadarod.currencyratechecker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.vadarod.currencyratechecker.entities.CurrencyDto;
import org.vadarod.currencyratechecker.services.CurrencyService;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/load-currency-rates/{ondate}")
    public ResponseEntity<String> loadCurrencyRates(@PathVariable("ondate") String date) {
        return currencyService.loadCurrencyRatesResponse(date);
    }

    @GetMapping("/get-currency-rate/{ondate}/{curId}")
    public ResponseEntity<CurrencyDto> findCurrencyDtoRate(@PathVariable("ondate") String date,
                                                           @PathVariable("curId") int curId) {
        return currencyService.findCurrencyDtoRateByDateAndCurIdResponse(date, curId);
    }
}