package Voice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoiceTest {
    @Test
    public void testCat(){
        Cat cat = new Cat();
        cat.voice();
    }

    @Test
    public void testDog(){
        Dog dog = new Dog();
        dog.voice();
    }

    @Test
    public void testCrow(){
        Crow crow = new Crow();
        crow.voice();
    }
}