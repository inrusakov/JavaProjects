package sample1;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {
    @Test
    void add_test(){
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        // the database name
        String dbName = "contactsDB";
        // define the Derby connection URL to use
        String connectionURL = "jdbc:derby:memory:" + dbName + ";create=true";

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

            Utility.addCommand(stat, new Contact("Rusakov", "Ivan",
                    "Nik","79057022470",
                    "-","Moscow","15.11.2001","DB",
                    "test"));
            ResultSet rs = Utility.listCommand(stat);
            Integer id = -1;
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
                break;
            }

            assertEquals(id, 1);

            conn.close();
            System.out.println("Closed connection");

            if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
                boolean gotSQLExc = false;
                try {
                    DriverManager.getConnection("jdbc:derby:memory:;shutdown=true");
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