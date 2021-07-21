package OOPTasks;

import org.junit.jupiter.api.Test;

class ArrayVectorImplTest {

    @Test
    public void test1(){
        ArrayVectorImpl impl = new ArrayVectorImpl();

        impl.set(13,23,421,421,123,123,123,53,23,4,5,5,6,6,4,3,3,4,4,5);
        impl.sortAscending();
    }

}