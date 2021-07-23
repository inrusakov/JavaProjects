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

    /**
     * Пустой конструктор.
     */
    public DateCalendar() {
        calendar = new GregorianCalendar();
        date = new Date();
        in = new Scanner(System.in);
    }

    /**
     * Получение даты из источника.
     * @throws ParseException При неправильном формате ввода данных.
     */
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

    /**
     * Метод для установки даты через строку.
     * @param info Строка с датой.
     * @throws ParseException При неправильном формате ввода даты.
     */
    public void setDate(String info) throws ParseException {
        in = new Scanner(info);
        getDateFromSource();
    }

    /**
     * @return Полученная дата.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Получение календаря из источника.
     * @throws ParseException При неправильном формате ввода календаря.
     */
    private void getCalendarFromSource() throws ParseException {
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int hours = in.nextInt();
        int minutes = in.nextInt();
        calendar = new GregorianCalendar(year,month-1,day,hours,minutes);
    }

    /**
     * Метод установки календаря через строку.
     * @param info Строка для календаря.
     * @throws ParseException При неправильном формате ввода.
     */
    public void setCalendar(String info) throws ParseException {
        in = new Scanner(info);
        getCalendarFromSource();
    }

    /**
     * @return Получение введенного календаря.
     */
    public Calendar getCalendar() {
        return calendar;
    }
}
