package Trivial;

public class ControlFlowStatements2Impl implements ControlFlowStatements2{
    @Override
    public int getFunctionValue(int x) {
        return (x<-2 || x>2)?(2*x):(-3*x);
    }

    @Override
    public String decodeMark(int mark) {
        switch(mark)
        {
            case(1):
                return "Fail";
            case(2):
                return "Poor";
            case(3):
                return "Satisfactory";
            case(4):
                return "Good";
            case(5):
                return "Excellent";
            default:
                return "Error";
        }
    }

    @Override
    public double[][] initArray() {
        double ret_arr[][] = new double[5][8];

        for(int i=0; i<5; i++)
            for(int j=0; j<8; j++)
                ret_arr[i][j] = Math.pow(i, 4) - Math.sqrt(j);

        return ret_arr;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double ret = array[0][0];

        for(int i=0; i<array.length; i++)
            for(int j=0; j<array[i].length; j++)
                ret = Math.max(ret, array[i][j]);

        return ret;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        Sportsman ret = new Sportsman();
        ret.addDay(10);
        float dist = 10;
        while(true)
        {
            dist *= P*0.01 + 1;
            ret.addDay(dist);

            if(ret.getTotalDistance() > 200)
                break;
        }
        return ret;
    }
}
