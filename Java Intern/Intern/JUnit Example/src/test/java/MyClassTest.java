import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @org.junit.jupiter.api.Test
    void getSum() {
        MyClass myClass = new MyClass();
        assertEquals(3,myClass.getSum(1,2));
        assertNotEquals(4,myClass.getSum(1,2));
    }

    @org.junit.jupiter.api.Test
    void zeroException() throws Exception {
        MyClass myClass = new MyClass();
        assertThrows(Exception.class, myClass::zeroException);
    }

    @org.junit.jupiter.api.Test
    void doSomething() {
        MyClass myClass = new MyClass();
        assertAll(myClass::doSomething);
    }

    @org.junit.jupiter.api.Test
    void alwaysTrue() {
        MyClass myClass = new MyClass();
        // Tests passed.
        assertTrue(myClass::alwaysTrue);
        // Tests failed.
        assertTrue(myClass::alwaysFalse);

    }

    @org.junit.jupiter.api.Test
    void alwaysFalse() {
        MyClass myClass = new MyClass();
        // Tests passed.
        assertFalse(myClass::alwaysFalse);
        // Tests failed.
        assertFalse(myClass::alwaysTrue);
    }

    @org.junit.jupiter.api.Test
    void superObject() {
        MyClass myClass = new MyClass();
        assertNull(myClass.nullObject());
    }
}