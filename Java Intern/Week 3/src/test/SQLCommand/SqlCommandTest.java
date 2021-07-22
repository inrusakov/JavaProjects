package SQLCommand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlCommandTest {

    @Test
    public void sql_test(){
        SqlCommand command = new SqlCommand("194", 3);
        Assertions.assertEquals("INSERT INTO T_GroupSelected (id_Student, firstName, lastName, id_Group)\n" +
                "SELECT id_Student, firstName, lastName, id_Group\n" +
                "FROM T_Student\n" +
                "WHERE id_Group = '194' AND dolgCount > 3", command.toString());
    }
}