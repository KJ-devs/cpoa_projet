package model.tools;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Date.from;

public class ConversionDate {
    public static Date LocalDateTodate(LocalDate date) {
        return from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    public static LocalDate DateToLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
