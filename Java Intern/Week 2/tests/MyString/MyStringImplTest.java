package MyString;

import org.junit.jupiter.api.Test;

class MyStringImplTest {

    @Test
    public void test1(){
        MyStringImpl myString = new MyStringImpl();
        for (int i = 0; i < 20; i++){
            myString.addLetter();
        }
        myString.show();
    }
}