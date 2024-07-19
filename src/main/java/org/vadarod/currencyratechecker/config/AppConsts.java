package org.vadarod.currencyratechecker.config;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class AppConsts {
    public static final int START_YEAR = 2024;
    public static final int START_MONTH = 7;
    public static final int START_DAY = 18;
    public static final int END_YEAR = LocalDate.now().getYear();
    public static final int END_MONTH = LocalDate.now().getMonthValue();
    public static final int END_DAY = LocalDate.now().getDayOfMonth();
    public static final int NUMBER_OF_MONTHS = Month.values().length;
    public static final String DATE_PATTERN = "yyyy-M-d";
    public static final int PERIODICITY = 0;
}