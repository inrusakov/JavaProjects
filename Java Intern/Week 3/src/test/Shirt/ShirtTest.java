package Shirt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShirtTest {

    @Test
    public void shirt_test(){
        Shirt shirt1 = new Shirt("S001,BlackPoloShirt,Black,XL");
        Shirt shirt2 = new Shirt("S002,Black Polo Shirt,Black,L");
        Shirt shirt3 = new Shirt("S003,Blue Polo Shirt,Blue,XL");
        Shirt shirt4 = new Shirt("S004,Blue Polo Shirt,Blue,M");
        Shirt shirt5 = new Shirt("S005,Tan Polo Shirt,Tan,XL");

        Assertions.assertEquals("ID: S001 Description: BlackPoloShirt Color: Black Size: XL ",
                shirt1.toString());
        Assertions.assertEquals("ID: S002 Description: Black Polo Shirt Color: Black Size: L ",
                shirt2.toString());
        Assertions.assertEquals("ID: S003 Description: Blue Polo Shirt Color: Blue Size: XL ",
                shirt3.toString());
        Assertions.assertEquals("ID: S004 Description: Blue Polo Shirt Color: Blue Size: M ",
                shirt4.toString());
        Assertions.assertEquals("ID: S005 Description: Tan Polo Shirt Color: Tan Size: XL ",
                shirt5.toString());
    }

}