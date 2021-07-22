package Person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void person_test1() {
        Person person1 = new Person(2001,11,23);

        Assertions.assertEquals("пятница 23 ноября 2001", person1.toString(DateType.FULL));
    }

    @Test
    void person_test2() {
        Person person1 = new Person(2001,11,23);
        Assertions.assertEquals("23 ноября 2001", person1.toString(DateType.MEDIUM));
    }

    @Test
    void person_test3() {
        Person person1 = new Person(2001,11,23);
        Assertions.assertEquals("23/11/2001", person1.toString(DateType.SHORT));
    }
}