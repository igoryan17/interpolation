/**
 * Created by igoryan on 09.12.15.
 */
public class InitialFunction implements Function {

    @Override
    public double getValue(double x) {
        double numerator =  8 * Math.pow(x, 3) + 10 * x + 1;
        double denominator = 1 + 25 * Math.pow(x, 2);
        return numerator / denominator;
    }
}
