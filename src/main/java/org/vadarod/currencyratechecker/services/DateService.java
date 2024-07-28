package org.vadarod.currencyratechecker.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.vadarod.currencyratechecker.config.AppConsts.*;

@Service
public class DateService {
    public static String formatDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return date.format(dateFormatter);
    }

    public static boolean hasMonthPassed(int year, int month) {
        return year == END_YEAR && month > END_MONTH;
    }

    public static boolean hasDayPassed(int year, int month, int day) {
        return year == END_YEAR && month == END_MONTH && day > END_DAY;
    }

    public static LocalDate parseDateFromStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}