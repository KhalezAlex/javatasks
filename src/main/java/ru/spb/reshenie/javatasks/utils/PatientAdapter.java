package ru.spb.reshenie.javatasks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class PatientAdapter {
    public static String getSexStr(int isMan) {
        return (isMan == 1) ? "МУЖ" : "ЖЕН";
    }

    public static String getPolicyStr(String smo, String policy) {
        return smo + " - " + policy;
    }

    public static String getAgeStr(LocalDate date) {
        return getAgeFormStr(LocalDate.now().compareTo(date));
    }

    private static String getAgeFormStr(int age) {
        int lastDigit = age % 10;
        if (age % 100 >= 11 && age % 100 <= 14)
            return String.valueOf(age).concat(" лет");
        if (lastDigit == 1)
            return String.valueOf(age).concat(" год");
        else if(lastDigit == 0 || lastDigit >= 5)
            return String.valueOf(age).concat(" лет");
        else if(lastDigit >= 2)
            return String.valueOf(age).concat(" года");
        return "";
    }

    public static String getDateFromSQLFormat(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dtf.format(date);
    }

    public static String getSnilsStr(String snils) {
        StringBuilder sb = new StringBuilder(snils);
        return sb.insert(3,"-").insert(7,"-").insert(11, "-").toString();
    }
}
