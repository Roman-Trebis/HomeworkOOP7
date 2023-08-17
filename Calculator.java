import java.util.logging.Level;
import java.util.logging.Logger;

interface ComplexNumber {
    ComplexNumber add(ComplexNumber num);
    ComplexNumber multiply(ComplexNumber num);
    ComplexNumber divide(ComplexNumber num);
}

class Complex implements ComplexNumber {
    private double real;
    private double imaginary;
    private static final Logger LOGGER = Logger.getLogger(Complex.class.getName());

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public ComplexNumber add(ComplexNumber num) {
        if (num instanceof Complex) {
            Complex complex = (Complex) num;
            return new Complex(this.real + complex.real, this.imaginary + complex.imaginary);
        } else {
            LOGGER.log(Level.WARNING, "Invalid ComplexNumber type for addition");
            return null;
        }
    }

    @Override
    public ComplexNumber multiply(ComplexNumber num) {
        if (num instanceof Complex) {
            Complex complex = (Complex) num;
            double newReal = (this.real * complex.real) - (this.imaginary * complex.imaginary);
            double newImaginary = (this.real * complex.imaginary) + (this.imaginary * complex.real);
            return new Complex(newReal, newImaginary);
        } else {
            LOGGER.log(Level.WARNING, "Invalid ComplexNumber type for multiplication");
            return null;
        }
    }

    @Override
    public ComplexNumber divide(ComplexNumber num) {
        if (num instanceof Complex) {
            Complex complex = (Complex) num;
            double divisor = (complex.real * complex.real) + (complex.imaginary * complex.imaginary);
            if (divisor != 0) {
                double newReal = ((this.real * complex.real) + (this.imaginary * complex.imaginary)) / divisor;
                double newImaginary = ((this.imaginary * complex.real) - (this.real * complex.imaginary)) / divisor;
                return new Complex(newReal, newImaginary);
            } else {
                LOGGER.log(Level.WARNING, "Division by zero is not allowed");
                return null;
            }
        } else {
            LOGGER.log(Level.WARNING, "Invalid ComplexNumber type for division");
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", real, imaginary);
    }
}

public class Calculator {
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getName());

    public static void main(String[] args) {
        ComplexNumber num1 = new Complex(2, 3);
        ComplexNumber num2 = new Complex(4, 5);

        ComplexNumber sum = num1.add(num2);
        ComplexNumber product = num1.multiply(num2);
        ComplexNumber division = num1.divide(num2);

        LOGGER.log(Level.INFO, "Sum: " + sum);
        LOGGER.log(Level.INFO, "Product: " + product);
        LOGGER.log(Level.INFO, "Division: " + division);
    }
}