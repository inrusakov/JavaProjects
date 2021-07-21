package Person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void getFirstName() {
        Person person = new Person("Ivan","Rusakov", "Nikolaevich");
        Assertions.assertEquals("Ivan",person.getFirstName());
    }

    @Test
    void getLastName() {
        Person person = new Person("Ivan","Rusakov", "Nikolaevich");
        Assertions.assertEquals("Rusakov",person.getLastName());
    }

    @Test
    void getPatronymicName() {
        Person person = new Person("Ivan","Rusakov", "Nikolaevich");
        Assertions.assertEquals("Nikolaevich", person.getPatronymicName());
    }

    @Test
    void testToString() {
        Person person = new Person("Ivan","Rusakov", "Nikolaevich");
        Assertions.assertEquals("Rusakov I.N.",person.toString());

        Person person2 = new Person("Ivan","Rusakov");
        Assertions.assertEquals("Rusakov I.", person2.toString());

        Person person3 = new Person("Rusakov");
        Assertions.assertEquals("Rusakov ", person3.toString());
    }
}