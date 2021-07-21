package Telephone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telephone {
    private final String format1 = "\\+([0-9])([0-9]{3})([0-9]{3})([0-9]{4})";
    private final String format2 = "(8)([0-9]{3})([0-9]{3})([0-9]{4})";
    private final String phoneNumber;

    /**
     * Конструктор с параметром строкой.
     * @param str Строка с телефоном.
     */
    public Telephone(String str) {
        Matcher m1 = Pattern.compile(format1).matcher(str);

        if (!m1.matches()) {
            m1 = Pattern.compile(format2).matcher(str);
            if (!m1.matches()) throw new IllegalArgumentException();
        }

        String  code;
        if (m1.group(1).equals("8"))
            code = "7";
        else
            code = m1.group(1);
        phoneNumber = String.format(
                "+%s%s-%s-%s",
                code,
                m1.group(2),
                m1.group(3),
                m1.group(4));
    }

    /**
     * @return Телефон.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
