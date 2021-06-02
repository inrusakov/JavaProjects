package sample1;

import java.io.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        // the database name
        String dbName = "contactsDB";
        // define the Derby connection URL to use
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";

        Connection conn = null;
        PreparedStatement psInsert;
        ResultSet myWishes;
        String printLine = "  __________________________________________________";
        String createString = "CREATE TABLE CONTACTS " +
                "(ID INTEGER NOT NULL PRIMARY KEY,"+
                "Surname VARCHAR(35) NOT NULL," +
                "Name VARCHAR(35) NOT NULL," +
                "Middle_name VARCHAR(35), " +
                "Mob_phone VARCHAR(15) NOT NULL," +
                "Home_phone VARCHAR(15) NOT NULL," +
                "Address VARCHAR(100)," +
                "Birth_day VARCHAR(15)," +
                "Notes VARCHAR(100))";
        try {
            conn = DriverManager.getConnection(connectionURL);
            System.out.println("Connected to database " + dbName);
            Statement stat = conn.createStatement();
            if (!Utility.checkForTable(conn)) {
                System.out.println(" . . . . creating table CONTACTS");
                stat.execute(createString);
            }

            String answer;
            do {
                System.out.println("Enter command or type \"help\" for help:");
                answer =  Utility.getCommand();
                if (answer.equals("help"))
                    Utility.typeHelp();
                if (answer.equals("list"))
                    Utility.listCommand(stat);
                if (answer.equals("add")) {
                    Utility.addCommand(stat, null);
                }
                if (answer.equals("delete")) {
                    Utility.deleteCommand(stat);
                }
                if (answer.equals("edit")) {
                    Utility.editCommand(stat);
                }
                if (answer.equals("about")) {
                    Utility.aboutCommand(stat);
                }
                if (answer.equals("import")) {
                    Utility.importCommand(stat);
                }
                if (answer.equals("export")) {
                    Utility.exportCommand(stat);
                }
                if (answer.equals("clear")) {
                    Utility.clearCommand(stat);
                }
            } while (!answer.equals("exit"));

            conn.close();
            System.out.println("Closed connection");

            if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
                boolean gotSQLExc = false;
                try {
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                } catch (SQLException se) {
                    if (se.getSQLState().equals("XJ015")) {
                        gotSQLExc = true;
                    }
                }
                if (!gotSQLExc) {
                    System.out.println("Database did not shut down normally");
                } else {
                    System.out.println("Database shut down normally");
                }
            }

        } catch (Throwable e) {
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
        }
        System.out.println("Getting Started With Derby JDBC program ending.");
    }
}
