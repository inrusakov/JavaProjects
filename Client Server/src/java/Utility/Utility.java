package Utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    /**
     * Получает значение хост из консоли.
     * @return Полученный хост.
     */
    public static String getHost() {
        System.out.println("Enter host:");
        return getCommand();
    }

    /**
     * Получает порт из консоли.
     * @return Полученный порт.
     */
    public static Integer getPort() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer ans = 0;
        System.out.println("Enter port:");
        try {
            while (ans == 0) {
                ans = Integer.parseInt(br.readLine());
                if (ans == 0)
                    System.out.print("Nothing entered: ");
            }
        } catch (java.io.IOException e) {
            System.out.println("Could not read response from stdin!");
        } catch (NumberFormatException e) {
            System.out.println("Wrong input format!");
            return getPort();
        }
        return ans;
    }

    /**
     * Введение команды в консоль.
     * @return введенная команда из консоли.
     */
    public static String getCommand() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = "";
        try {
            while (ans.length() == 0) {
                ans = br.readLine();
                if (ans.length() == 0)
                    System.out.print("Nothing entered: ");
            }
        } catch (java.io.IOException e) {
            System.out.println("Could not read response from stdin");
        }
        return ans;
    }

    /**
     * Проверка данных через регулярные выражения.
     * @param pattern Паттерн для проверки выражения.
     * @param data Данные для проверки.
     * @return Проходит ли значение или нет.
     */
    public static boolean dataChecker(String pattern, String data) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(data);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * Проверка пути к файлу.
     * @param line Строка с путем к файлу.
     * @return Проходит ли проверка или нет.
     */
    public static boolean fileChecker(String line) {
        if (line.contains("\\") || line.contains("/") || line.contains(":") ||
                line.contains("*") || line.contains("?") || line.contains("\"") || line.contains("<") ||
                line.contains(">") || line.contains("|") || (line.contains(",") && !line.contains("."))) {
            return false;
        }
        return true;
    }
}
