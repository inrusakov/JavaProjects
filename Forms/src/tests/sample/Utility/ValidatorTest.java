package sample.Utility;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validation_test1() {
        Parent papa = new Parent("Ivan", "Rusakov", 45, "Russia");
        GuestForm guest = new GuestForm("Ivan",
                "Rusakov",
                19, List.of("Tv", "Radio", "WiFi"),
                "Russia", papa);
        Validator validator = new Validator();
        Set<ValidationError> report = validator.validate(guest);
        assertEquals(report.size(), 3);
    }

    @Test
    void validation_test2() {
        Parent papa = new Parent("Ivan", "Rusakov", 22, "Russia");
        GuestForm guest = new GuestForm("Ivan",
                "Rusakov",
                15, List.of("WC", "Chair", "WiFi"),
                "France", papa);
        Validator validator = new Validator();
        Set<ValidationError> report = validator.validate(guest);
        assertEquals(report.size(), 5);
    }

    @Test
    void validation_test3() {
        Parent papa = new Parent("Ivan", "", 16, "Russia");
        List<GuestForm> guests = List.of(
                new GuestForm(null,
                        "Kuk",
                        21,
                        List.of("WC", "Chair", "WiFi"),
                        "Russia",
                        papa),
                new GuestForm(" ",
                        "Def",
                        -3,
                        List.of("A", "B", "C"),
                        "UK",
                        null)
        );
        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Piano"),
                "Apartment");
        Validator validator = new Validator();
        Set<ValidationError> validationErrors = validator.validate(bookingForm);
    }

    @Test
    void validation_test4() {
        Parent papa = new Parent("Ivan", "Rusakov", null, "Russia");
        Validator validator = new Validator();
        Set<ValidationError> report = validator.validate(papa);
        for (ValidationError error : report) {
            assertEquals(error.getPath(), "Parent.age");
            assertEquals(error.getMessage(), "Parent.age cannot be null!");
            assertEquals(error.getFailedValue(), papa);
        }   
    }

    @Test
    void validation_test5() {
        Point point = new Point(1, -1, List.of(-3, -2, -1, 0, 1, 2, 3));
        Validator validator = new Validator();
        Set<ValidationError> report = validator.validate(point);
        assertEquals(report.size(), 5);
    }
}