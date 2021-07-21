package OOPTasks;

public class EmployeeImpl implements Employee{

    private String name;
    private String lastname;
    private Integer salary;
    private Employee manager;

    public EmployeeImpl() {
        salary = 1000;
    }

    /**
     * @return Зарплата сотрудника на настоящий момент.
     */
    @Override
    public int getSalary() {
        return salary;
    }

    /**
     * Увеличивает зарплату сотрудника на заданное значение
     * @param value Значение, на которое нужно увеличить
     */
    @Override
    public void increaseSalary(int value) {
        salary+= value;
    }

    /**
     * @return Имя сотрудника
     */
    @Override
    public String getFirstName() {
        return name;
    }

    /**
     * Устанавливает имя сотрудника
     * @param firstName Новое имя
     */
    @Override
    public void setFirstName(String firstName) {
        name = firstName;
    }

    /**
     * @return Фамилия сотрудника
     */
    @Override
    public String getLastName() {
        return lastname;
    }

    /**
     * Устанавливает фамилию сотрудника
     * @param lastName Новая фамилия
     */
    @Override
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    /**
     * @return Имя и затем фамилия сотрудника, разделенные символом " " (пробел)
     */
    @Override
    public String getFullName() {
        return name+" "+lastname;
    }

    /**
     * Устанавливает Менеджера сотрудника.
     * @param manager Сотрудник, являющийся менеджером данного сотрудника.
     */
    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * @return Имя и фамилия Менеджера, разделенные символом " " (пробел).
     */
    @Override
    public String getManagerName() {
        if (manager!= null)
            return manager.getFullName();
        return "No manager";
    }

    /**
     * Возвращает Менеджера верхнего уровня, т.е. вершину иерархии сотрудников,
     *   в которую входит данный сотрудник.
     */
    @Override
    public Employee getTopManager() {
        if (manager != null)
            return manager.getTopManager();
        return this;
    }
}
