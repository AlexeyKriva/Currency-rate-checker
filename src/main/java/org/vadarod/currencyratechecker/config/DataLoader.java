package org.vadarod.currencyratechecker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.vadarod.currencyratechecker.services.CurrencyService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CurrencyService currencyService;
    @Override
    public void run(String... args) throws Exception {
        currencyService.loadCurrencyRates();
    }
}