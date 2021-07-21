package Person;

public class Person {
    private String firstName;
    private String lastName;
    private String patronymicName;

    /**
     * Конструктор только с фамилией.
     * @param lastName Фамилия.
     */
    public Person(String lastName) {
        this.lastName = lastName;
        firstName = "";
        patronymicName = "";
    }

    /**
     * Конструктор с именем и фамилией.
     * @param firstName Имя
     * @param lastName Фамилия
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        patronymicName = "";
    }

    /**
     * Конструктор со всеми параметрами.
     * @param firstName Имя
     * @param lastName Фамилия
     * @param patronymicName Отчество
     */
    public Person(String firstName, String lastName, String patronymicName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
    }

    /**
     * @return Имя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Фамилия
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return Отчество
     */
    public String getPatronymicName() {
        return patronymicName;
    }

    /**
     * @return Строковое преобразование ФИО.
     */
    @Override
    public String toString() {
        if (!patronymicName.equals("") && !firstName.equals("")) {
            patronymicName = patronymicName.charAt(0) + ".";
            firstName = firstName.charAt(0) + ".";
        } else if (patronymicName.equals("") && !firstName.equals("")) {
            firstName = firstName.charAt(0) + ".";
        }
        else{
            firstName = "";
            patronymicName = "";
        }
        return String.format("%s %s%s", lastName, firstName, patronymicName);
    }
}
