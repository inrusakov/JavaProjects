package OOPTasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeImplTest {

    @Test
    public void test1(){
        EmployeeImpl worker = new EmployeeImpl();
        worker.setFirstName("1");
        EmployeeImpl man = new EmployeeImpl();
        man.setFirstName("2");
        EmployeeImpl babe = new EmployeeImpl();
        babe.setFirstName("3");
        man.setManager(babe);
        worker.setManager(man);

        System.out.println(worker.getTopManager().getFullName());
    }
}