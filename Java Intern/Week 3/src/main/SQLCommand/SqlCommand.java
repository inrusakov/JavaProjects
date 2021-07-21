package SQLCommand;

public class SqlCommand {
    private String debtAmount;
    private String groupId;

    /**
     * Конструктор с параметрами.
     * @param groupId Группа студента.
     * @param debt Нужный параметр долга студента.
     */
    public SqlCommand(String groupId,int debt ) {
        this.debtAmount = String.valueOf(debt);
        this.groupId = groupId;
    }

    /**
     * @return Строковое спредставление сформированной команды.
     */
    @Override
    public String toString() {
        return String.format("INSERT INTO T_GroupSelected (id_Student, firstName, lastName, id_Group)\n" +
                "SELECT id_Student, firstName, lastName, id_Group\n" +
                "FROM T_Student\n" +
                "WHERE id_Group = \'%s\' AND dolgCount > %s", groupId, debtAmount);
    }
}
