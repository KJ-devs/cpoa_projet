package model.tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Date.from;

public class ConversionDate {
    public static Date DateToLocalDate(LocalDate date) {
        return from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    public static LocalDate LocalDateToDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
