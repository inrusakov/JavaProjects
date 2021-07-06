package SquareEquation;

import SquareEquation.Equation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @Test
    public void test1(){
        Equation equation = new Equation(-2,3,5);
        assertEquals(equation.getResults().length,2);
        assertEquals(equation.getResults()[0],-1);
        assertEquals(equation.getResults()[1],2.5);
    }

    @Test
    public void test2(){
        Equation equation = new Equation(2,3,5);
        assertEquals(equation.getResults().length,0);
    }

    @Test
    public void test3(){
        Equation equation = new Equation(2,4,2);
        assertEquals(equation.getResults().length,1);
        assertEquals(equation.getResults()[0],-1);
    }
}