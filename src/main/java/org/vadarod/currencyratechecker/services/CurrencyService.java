package org.vadarod.currencyratechecker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vadarod.currencyratechecker.clients.NbrbClient;
import static org.vadarod.currencyratechecker.config.AppConsts.*;
import static org.vadarod.currencyratechecker.services.DateService.*;

import org.vadarod.currencyratechecker.entities.Currency;
import org.vadarod.currencyratechecker.entities.CurrencyDto;
import org.vadarod.currencyratechecker.repositories.CurrencyRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private NbrbClient nbrbClient;

    public boolean loadCurrencyRates() {
        for (int currentYear = START_YEAR; currentYear <= END_YEAR; currentYear++) {
            for (int currentMonth = START_MONTH; currentMonth <= NUMBER_OF_MONTHS; currentMonth++) {
                if (hasMonthPassed(currentYear, currentMonth)) {
                    break;
                }
                for (int currentDay = START_DAY; currentDay <= YearMonth.of(currentYear, currentMonth).lengthOfMonth();
                     currentDay++) {
                    if (hasDayPassed(currentYear, currentMonth, currentDay)) {
                        break;
                    }
                    List<CurrencyDto> currencyRatesFromNbrbApi =
                            nbrbClient.getCurrencyRatesByDate(formatDate(currentYear, currentMonth, currentDay),
                                    PERIODICITY);
                    System.out.println(currencyRatesFromNbrbApi);
                    saveCurrencyDtoRates(currencyRatesFromNbrbApi);
                }
            }
        }
        return true;
    }

    public ResponseEntity<String> loadCurrencyRatesResponse(String date) {
        if (loadCurrencyRates(date)) {
            return ResponseEntity.ok("Currency rates were successfully loaded for date: " + date);
        } else {
            return ResponseEntity.badRequest().body("Currency rates were not successfully loaded for date: " + date);
        }
    }

    public boolean loadCurrencyRates(String date) {
        LocalDate localDate = parseDateFromStringToLocalDate(date);
        if (currencyRepository.existsCurrencyByDate(LocalDateTime.of(localDate.getYear(), localDate.getMonth(),
                localDate.getDayOfMonth(), 0, 0, 0))) {
            return true;
        } else {
            List<CurrencyDto> currencyRatesFromNbrbApi =
                    nbrbClient.getCurrencyRatesByDate(date,
                            PERIODICITY);
            return saveCurrencyDtoRates(currencyRatesFromNbrbApi);
        }
    }

    private Currency fromCurrencyDtoToCurrency(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        currency.setCurId(currencyDto.getCurId());
        currency.setDate(currencyDto.getDate());
        currency.setAbbreviation(currencyDto.getAbbreviation());
        currency.setScale(currencyDto.getScale());
        currency.setName(currencyDto.getName());
        currency.setOfficialRate(currencyDto.getOfficialRate());
        return currency;
    }

    private CurrencyDto fromCurrencyToCurrencyDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setDate(currency.getDate());
        currencyDto.setAbbreviation(currency.getAbbreviation());
        currencyDto.setScale(currency.getScale());
        currencyDto.setName(currency.getName());
        currencyDto.setOfficialRate(currency.getOfficialRate());
        currencyDto.setCurId(currency.getCurId());
        return currencyDto;
    }

    public boolean saveCurrencyDtoRates(List<CurrencyDto> currencyRatesFromNbrbApi) {
        for (CurrencyDto currencyDto: currencyRatesFromNbrbApi) {
            Currency currency = fromCurrencyDtoToCurrency(currencyDto);
            saveCurrencyRate(currency);
        }
        return !currencyRatesFromNbrbApi.isEmpty();
    }

    public boolean saveCurrencyRate(Currency currency) {
        currencyRepository.save(currency);
        return true;
    }

    public ResponseEntity<CurrencyDto> findCurrencyDtoRateByDateAndCurIdResponse(String date, int curId) {
        CurrencyDto currencyDto = findCurrencyDtoRateByDateAndCurId(date, curId);
        if (currencyDto != null) {
            return ResponseEntity.ok(currencyDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public CurrencyDto findCurrencyDtoRateByDateAndCurId(String date, int curId) {
        LocalDate localDate = parseDateFromStringToLocalDate(date);
        Optional<Currency> currency = currencyRepository.findCurrencyByDateAndCurId(localDate.atStartOfDay(), curId);
        if (currency.isPresent()) {
            CurrencyDto currencyDto = fromCurrencyToCurrencyDto(currency.get());
            return currencyDto;
        }
        return null;
    }
}