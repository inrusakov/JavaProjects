package sample1;

import java.io.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
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

    public static void typeHelp() {
        System.out.println("• list - вывести список сохраненных контактов\n" +
                "• add - добавить новый контакт\n" +
                "• delete - удалить контакт\n" +
                "• edit - изменить контакт\n" +
                "• about - вывести текст привычного диалога \"О программе\", где указан её автор и послание\n" +
                "потомкам\n" +
                "• import - импорт всех контактов из файла data.csv в папке DB\n" +
                "• export - экспорт всех контактов в файл data.csv в папке DB\n" +
                "• exit - выход");
    }

    public static ResultSet listCommand(Statement stat) {
        try {
            stat.execute("SELECT * FROM CONTACTS");
            ResultSet rs = stat.getResultSet();
            showResultSet(rs);
            return rs;
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
            return null;
        }
    }

    public static void addCommand(Statement stat, Contact temp) {
        if (temp != null) {
            try {
                stat.execute("INSERT INTO CONTACTS VALUES (" +
                        temp.id + "," +
                        "'" + temp.surname + "'" + "," +
                        "'" + temp.name + "'" + "," +
                        "'" + temp.middleName + "'" + "," +
                        "'" + temp.cellPhone + "'" + "," +
                        "'" + temp.homePhone + "'" + "," +
                        "'" + temp.address + "'" + "," +
                        "'" + temp.birthDay + "'" + "," +
                        "'" + temp.notes + "'" + ")");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                ResultSet rs = stat.executeQuery("SELECT * FROM CONTACTS ORDER BY ID DESC");
                Integer id = 0;
                while (rs.next()) {
                    id = Integer.parseInt(rs.getString(1));
                    break;
                }
                Contact contact = new Contact(id);
                if (id != 0)
                    contact.increment();
                try {
                    contact = new Contact(
                            getInfo("Surname", 1),
                            getInfo("Name", 1),
                            getInfo("Middle Name", 0),
                            getInfo("Cell Phone (Ориетированое на российские номера пример:+79012345678)", 0),
                            getInfo("Home Phone (Ориетированое на российские номера пример:+79012345678)", 0),
                            getInfo("Address", 0),
                            getInfo("Birth Day (DD.MM.YYYY)", 0),
                            getInfo("Notes", 0)
                    );

                    stat.execute("INSERT INTO CONTACTS VALUES (" +
                            contact.id + "," +
                            "'" + contact.surname + "'" + "," +
                            "'" + contact.name + "'" + "," +
                            "'" + contact.middleName + "'" + "," +
                            "'" + contact.cellPhone + "'" + "," +
                            "'" + contact.homePhone + "'" + "," +
                            "'" + contact.address + "'" + "," +
                            "'" + contact.birthDay + "'" + "," +
                            "'" + contact.notes + "'" + ")");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    contact = new Contact();
                }
                contact.increment();
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown:");
                e.printStackTrace(System.out);
            }
        }
    }

    public static void deleteCommand(Statement stat) {
        try {
            System.out.println("Enter contact ID:");
            String id = getCommand();
            stat.execute("DELETE FROM CONTACTS WHERE ID = " + id);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
        }
    }

    public static void editCommand(Statement stat) {
        try {
            System.out.println("Enter contact ID:");
            String id = getCommand();

            String editID = "";
            String pattern = "[1-8]";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(editID);
            while (!m.find()) {
                System.out.println("What do you want to edit?\n" +
                        "1. Surname\n" +
                        "2. Name\n" +
                        "3. Middle Name\n" +
                        "4. Cell Phone\n" +
                        "5. Home Phone\n" +
                        "6. Address\n" +
                        "7. Birth day\n" +
                        "8. Notes");
                editID = getCommand();
                m = r.matcher(editID);
            }

            String newInfo = "";
            String type = "";
            switch (editID) {
                case ("1"):
                    newInfo = getInfo("New Surname", 1);
                    type = "Surname";
                    break;
                case ("2"):
                    newInfo = getInfo("New Name", 1);
                    type = "Name";
                    break;
                case ("3"):
                    newInfo = getInfo("New Middle Name", 1);
                    type = "Middle_name";
                    break;
                case ("4"):
                    newInfo = getInfo("New Cell Phone", 1);
                    type = "Mob_phone";
                    if (!Contact.dataChecker("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
                            newInfo))
                        throw new IllegalArgumentException("Wrong cell phone parameter!");
                    break;
                case ("5"):
                    newInfo = getInfo("New Home Phone", 1);
                    type = "Home_phone";
                    if (!Contact.dataChecker("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
                            newInfo))
                        throw new IllegalArgumentException("Wrong home phone parameter!");
                    break;
                case ("6"):
                    newInfo = getInfo("NewAddress", 1);
                    type = "Address";
                    break;
                case ("7"):
                    newInfo = getInfo("New Birth Day", 1);
                    type = "Birth_day";
                    if (!Contact.dataChecker("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)",
                            newInfo))
                        throw new IllegalArgumentException("Wrong birth day parameter!");
                    break;
                case ("8"):
                    newInfo = getInfo("New Notes", 1);
                    type = "Notes";
                    break;
                default:
                    break;
            }
            String command = "UPDATE CONTACTS SET " + type + "=" + "'" + newInfo + "'" + " WHERE ID=" + id;
            stat.execute(command);
        } catch (Exception e) {
            System.out.println(" . . . exception thrown:");
            System.out.println(e.getMessage());
        }
    }

    public static void aboutCommand(Statement stat) {
        System.out.println("Телефонная книга. Автор - Русаков Иван БПИ194.");
    }

    public static void importCommand(Statement stat) {
        File file = new File("data.csv");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                if ((s = in.readLine()) != null)
                    while ((s = in.readLine()) != null) {
                        String[] params = s.split(",");
                        Contact contact;
                        try {
                            contact = new Contact(params[1], params[2], params[3], params[4],
                                    params[5], params[6], params[7], params[8]);
                            stat.execute("INSERT INTO CONTACTS VALUES (" +
                                    contact.id + "," +
                                    "'" + contact.surname + "'" + "," +
                                    "'" + contact.name + "'" + "," +
                                    "'" + contact.middleName + "'" + "," +
                                    "'" + contact.cellPhone + "'" + "," +
                                    "'" + contact.homePhone + "'" + "," +
                                    "'" + contact.address + "'" + "," +
                                    "'" + contact.birthDay + "'" + "," +
                                    "'" + contact.notes + "'" + ")");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            contact = new Contact();
                        }
                        contact.increment();
                    }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    public static void exportCommand(Statement stat) {
        File file = new File("data.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            stat.execute("SELECT * FROM CONTACTS");
            ResultSet rs = stat.getResultSet();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                sb.append(metaData.getColumnLabel(i));
                if (i != columnCount)
                    sb.append(",");
            }
            out.println(sb.toString());
            sb.setLength(0);

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    sb.append(rs.getString(i));
                    if (i != columnCount)
                        sb.append(",");
                }
                out.println(sb.toString());
                sb.setLength(0);
            }
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
        }
    }

    public static void clearCommand(Statement stat) {
        try {
            stat.execute("TRUNCATE TABLE CONTACTS");
            Contact contact = new Contact();
            contact.clear();
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
        }
    }

    public static String getInfo(String infoType, int priority) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String info = "";
        while (info.length() == 0) {
            if (priority == 0)
                System.out.println("Enter " + infoType + " (or -):");
            if (priority == 1)
                System.out.println("Enter " + infoType + " :");
            info = br.readLine();
            if (info.length() == 0)
                System.out.print("Nothing entered: ");
            if (info.equals("-") && priority == 0)
                return info;
        }
        return info;
    }

    public static void showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print("| ");
            System.out.print(metaData.getColumnLabel(i));
            if (i > 1) System.out.print(" ");

        }
        System.out.print("|");
        System.out.println();
        System.out.print("|");
        System.out.print("---|");
        System.out.print("---------------------------------------------------------------------------|");
        System.out.println();

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("| ");
                System.out.print(result.getString(i));
                if (i > 1) System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public static boolean checkForTable(Connection conTst) throws SQLException {
        try {
            Statement s = conTst.createStatement();
            s.execute("SELECT * FROM CONTACTS");
        } catch (SQLException sqle) {
            String theError = (sqle).getSQLState();
            if (theError.equals("42X05"))   // Table does not exist
            {
                return false;
            } else if (theError.equals("42X14") || theError.equals("42821")) {
                System.out.println("Table checker: Incorrect table definition. Drop table CONTACTS and rerun this program");
                throw sqle;
            } else {
                System.out.println("Table checker: Unhandled SQLException");
                throw sqle;
            }
        }
        return true;
    }
}
