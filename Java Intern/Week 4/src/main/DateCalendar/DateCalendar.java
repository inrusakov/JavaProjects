package DateCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateCalendar {
    private Calendar calendar;
    private Date date;
    private Scanner in;

    public DateCalendar() {
        calendar = new GregorianCalendar();
        date = new Date();
        in = new Scanner(System.in);
    }


    private void getDateFromSource() throws ParseException {
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int hours = in.nextInt();
        int minutes = in.nextInt();
        this.date = new SimpleDateFormat("dd/MM/yyyy/HH:mm").parse(
                day + "/" + month + "/"
                + year + "/" + hours + ":" + minutes);
    }

    public void setDate(String info) throws ParseException {
        in = new Scanner(info);
        getDateFromSource();
    }

    public Date getDate() {
        return date;
    }

    private void getCalendarFromSource() throws ParseException {
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int hours = in.nextInt();
        int minutes = in.nextInt();
        calendar = new GregorianCalendar(year,month-1,day,hours,minutes);
    }

    public void setCalendar(String info) throws ParseException {
        in = new Scanner(info);
        getCalendarFromSource();
    }

    public Calendar getCalendar() {
        return calendar;
    }
}
