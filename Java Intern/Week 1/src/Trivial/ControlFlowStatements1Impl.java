package Trivial;

public class ControlFlowStatements1Impl implements ControlFlowStatements1{
    @Override
    public float getFunctionValue(float x) {
        if(x>0)
            return (float) (2*Math.sin((double) x));
        else
            return 6-x;
    }

    @Override
    public String decodeWeekday(int weekday) {
        switch(weekday){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return null;

        }
    }

    @Override
    public int[][] initArray() {
        int ret[][] = new int[8][5];
        for(int i=0; i<8; i++)
            for(int j=0; j<5; j++)
                ret[i][j] = i*j;
        return ret;
    }

    @Override
    public int getMinValue(int[][] array) {
        int ret = array[0][0];

        for(int i=0; i < array.length; i++)
            for(int j=0; j < array[i].length; j++ )
                ret = Math.min(ret, array[i][j]);

        return ret;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit ret = new BankDeposit();
        ret.amount = 1000;
        ret.years = 0;
        while(true)
        {
            ret.amount *= (P*0.01 + 1);
            ++ret.years;
            if(ret.amount > 5000)
                break;
        }
        return ret;
    }
}
