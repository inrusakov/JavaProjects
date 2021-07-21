package Extended;

import org.junit.jupiter.api.Test;

class ExtendedClassTest {

    @Test
    public void test_Extended(){
        Byte b = 123;
        ExtendedClass extendedClass = new ExtendedClass(b,2,2.3,"asf");
        System.out.println(extendedClass);
    }

}