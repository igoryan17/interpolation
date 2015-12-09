/**
 * Created by igoryan on 09.12.15.
 */
public class Polynomial implements Function {
    private int N;
    private Point[] points;
    private Function function;

    public Polynomial(int n, boolean cheb) {
        N = n;
        function = new InitialFunction();
        initPoints(N, cheb);
    }

    @Override
    public double getValue(double x) {
        double result = 0;
        for (int i = 0; i < N; ++i) {
            Function supportPolynomial = new SupportPolynomials(i);
            result += points[i].getY() * supportPolynomial.getValue(x);
        }
        return result;
    }

    private void initPoints(int N, boolean cheb) {
        points = new Point[N];
        PointGetter getterPoints;
        if (cheb) {
            getterPoints = new PointGetter() {
                @Override
                public double getPoint(int i) {
                    return Math.cos((2 * i + 1) * Math.PI / (2.0 * (double) N));
                }
            };
        } else {
            getterPoints = new PointGetter() {
                @Override
                public double getPoint(int i) {
                    double step = 2.0 / (double) N;
                    return -1 + i * step;
                }
            };
        }
        for (int i = 0; i < N; ++i) {
            double x = getterPoints.getPoint(i);
            points[i] = new Point(x, function.getValue(x));
        }
    }

    private interface PointGetter {
        double getPoint(int i);
    }

    class SupportPolynomials implements Function {
        int k;

        public SupportPolynomials(int k) {
            this.k = k;
        }

        @Override
        public double getValue(double x) {
            double xk = points[k].getX();
            double numerator = 1;
            for (int i = 0; i < N; ++i) {
                if (i != k) {
                    numerator *= x - points[i].getX();
                }
            }
            double denominator = 1;
            for (int i = 0; i < N; ++i) {
                if (i != k) {
                    denominator *= xk - points[i].getX();
                }
            }
            return numerator / denominator;
        }
    }
}
