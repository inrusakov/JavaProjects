package DateCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


class DateCalendarTest {

    @Test
    public void date_test() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        DateCalendar dateCalendar1 = new DateCalendar();
        dateCalendar1.setDate("2001 11 23 18 23");

        Assertions.assertEquals("23/11/2001 18:23", dateFormatter.format(dateCalendar1.getDate()));
    }

    @Test
    public void date_calendar() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        DateCalendar dateCalendar2 = new DateCalendar();
        dateCalendar2.setCalendar("2001 11 23 18 23");

        Assertions.assertEquals("23/11/2001 18:23", dateFormatter.format(dateCalendar2.getCalendar().getTime()));
    }
}