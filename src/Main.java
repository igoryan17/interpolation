import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by igoryan on 09.12.15.
 */
public class Main {
    private static final int FIRST_POINTS_COUNT = 10;
    private static final int SECOND_POINTS_COUNT = 100;
    private static final String FIRST_POLYNOMIAL = "first polynomial.txt";
    private static final String SECOND_POLYNOMIAL = "second polynomial.txt";
    private static final String FIRST_CHEB = "first cheb.txt";
    private static final String SECOND_CHEB = "second cheb.txt";
    private static final int POINTS_COUNT = 1000;

    public static void main(String args[]) {
        Function startFunction = new InitialFunction();
        Function firstEvenlyPolynomial = new Polynomial(FIRST_POINTS_COUNT, false);
        Function secondEvenlyPolynomial = new Polynomial(SECOND_POINTS_COUNT, false);
        Function firstChebyshevPolynomial = new Polynomial(FIRST_POINTS_COUNT, true);
        Function secondChebyshevPolynomial = new Polynomial(SECOND_POINTS_COUNT, true);
        File first_polynomial = new File(FIRST_POLYNOMIAL);
        File second_polynomial = new File(SECOND_POLYNOMIAL);
        File first_cheb = new File(FIRST_CHEB);
        File second_cheb = new File(SECOND_CHEB);
        try {
            writePoints(first_polynomial, firstEvenlyPolynomial);
            writePoints(second_polynomial, secondEvenlyPolynomial);
            writePoints(first_cheb, firstChebyshevPolynomial);
            writePoints(second_cheb, secondChebyshevPolynomial);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writePoints(File file, Function f) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < POINTS_COUNT; ++i) {
            double step = 2.d / (double) POINTS_COUNT;
            double x = -1 + i * step;
            printWriter.println(x + " " + f.getValue(x));
        }
        printWriter.close();
    }
}
