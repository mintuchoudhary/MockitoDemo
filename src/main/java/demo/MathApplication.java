package demo;

/**
 * Created by mich0916 on 1/24/2017.
 */
public class MathApplication {
    private CalculatorService calcService;

    public void setCalculatorService(CalculatorService calcService){
        this.calcService = calcService;
    }

    public int add(int input1, int input2){
        return calcService.add(input1, input2);
    }

    public int subtract(int input1, int input2){
        return calcService.subtract(input1, input2);
    }

    public int multiply(int input1, int input2){
        return calcService.multiply(input1, input2);
    }

    public int divide(int input1, int input2){
        return calcService.divide(input1, input2);
    }
}
