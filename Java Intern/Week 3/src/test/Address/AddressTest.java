package Address;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void split_test(){
        Address address1 = new Address("Russia",
                "Moscow",
                "Moscow",
                "Tverskaya",
                "2",
                "1",
                "154");

        Address address2 = new Address("Russia, Moscow, Moscow, Tverskaya, 2, 1, 154");
        Address address3 = new Address("Russia; Moscow; Moscow; Tverskaya; 2; 1; 154");
        Address address4 = new Address("Russia- Moscow- Moscow- Tverskaya- 2- 1- 154");
        Address address5 = new Address("Russia, Moscow. Moscow, Tverskaya; 2- 1; 154");

        assertEquals(address1.toString(), address2.toString());
        assertEquals(address1.toString(), address3.toString());
        assertEquals(address1.toString(), address4.toString());
        assertEquals(address1.toString(), address5.toString());
    }
}