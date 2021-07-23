package Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Person {
    private Date dateOfBirth;

    /**
     * @param year Год рождения.
     * @param month Месяц.
     * @param day День.
     */
    public Person(int year,int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        dateOfBirth = calendar.getTime();
    }

    /**
     * Метод toString.
     * @param type Тип форматирования.
     * @return String с данными.
     */
    public String toString(DateType type){
        SimpleDateFormat formatter;
        switch (type){
            case FULL:
                formatter = new SimpleDateFormat("EEEEEEE d MMMMMMM yyyy");
                return formatter.format(dateOfBirth);
            case MEDIUM:
                formatter = new SimpleDateFormat("dd MMMMM yyyy");
                return formatter.format(dateOfBirth);
            case SHORT:
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                return formatter.format(dateOfBirth);
            default:
                return null;
        }
    }
}
