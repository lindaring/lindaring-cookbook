package za.co.lindaring.types;

import lombok.Getter;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.YEAR;

@Getter
public class CookbookDate {

    private static final String DEFAULT_FORMAT = "dd MMM yyyy";

    private int day;
    private int month;
    private int year;
    private Date date;
    private Calendar calendar;

    public CookbookDate() {
        date = new Date(System.currentTimeMillis());
    }

    public CookbookDate(Date date) {
        this.date = date;
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);

            year = calendar.get(YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
        }
    }

    public String formatDate() {
        return DateFormatUtils.format(date, DEFAULT_FORMAT);
    }

    public String formatDate(String format) {
        return DateFormatUtils.format(date, format);
    }

    public Date toStartOfDay() {
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    public Date toEndOfDay() {
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

}