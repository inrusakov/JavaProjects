package Telephone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneTest {

    @Test
    public void phone_test(){
        Telephone p = new Telephone("88005553535");
        assertEquals("+7800-555-3535", p.getPhoneNumber());
    }

    @Test
    public void phone_test2(){
        Telephone p = new Telephone("+19007303223");
        assertEquals("+1900-730-3223", p.getPhoneNumber());
    }
}