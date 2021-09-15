public class MyClass {

    public double getSum(double valueA, double valueB){
        return valueA+valueB;
    }

    public void zeroException() throws Exception{
        int a = 1;
        int b = 0;
        int c = a/b;
    }

    public void doSomething(){
        System.out.println("I did something!");
    }

    public boolean alwaysTrue(){
        return true;
    }

    public boolean alwaysFalse(){
        return false;
    }

    public Object nullObject(){
        Object obj = null;
        return obj;
    }
}
