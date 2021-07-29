package OOPTasks;

import java.util.Arrays;
import java.util.Comparator;

public class ComplexNumberImpl implements ComplexNumber {
    private double real;
    private double im;

    public ComplexNumberImpl() {
        real = 0;
        im = 0;
    }

    public ComplexNumberImpl(double re, double im) {
        this.real = re;
        this.im = im;
    }

    @Override
    public double getRe() {
        return real;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        return (im == 0);
    }

    @Override
    public void set(double re, double im) {
        this.real = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        int imPtEnd = value.indexOf("i");
        if (imPtEnd == -1) {
            real = Double.parseDouble(value);
            im = 0;
            return;
        }

        if (imPtEnd != value.length() - 1) {
            throw new NumberFormatException();
        }

        int temp;
        for (temp = 1; imPtEnd - temp >= 0
                && (Character.isDigit(value.charAt(imPtEnd - temp))
                || value.charAt(imPtEnd - temp) == '.'); ) {
            temp++;
        }

        int imPtStart = imPtEnd - temp + 1;
        if (imPtStart == 0) {
            real = 0;
            if (imPtStart == imPtEnd) {
                im = Double.parseDouble(value.substring(0, imPtEnd) + "1");
            } else {
                im = Double.parseDouble(value.substring(0, imPtEnd));
            }
        } else {
            if (imPtStart == imPtEnd) {
                im = Double.parseDouble(value.substring(imPtStart - 1, imPtEnd) + "1");
            } else {
                im = Double.parseDouble(value.substring(imPtStart - 1, imPtEnd));
            }

            if (imPtStart - 1 != 0) {
                real = Double.parseDouble(value.substring(0, imPtStart - 1));
            } else {
                real = 0;
            }
        }
    }

    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(real, im);
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumber) super.clone();
    }

    @Override
    public int compareTo(ComplexNumber other) {
        if (Math.hypot(real, im) - Math.hypot(other.getRe(), other.getIm()) > 0)
            return 1;
        else if (Math.hypot(real, im) - Math.hypot(other.getRe(), other.getIm()) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array, new Comparator<ComplexNumber>() {
            @Override
            public int compare(ComplexNumber prev, ComplexNumber other) {
                return prev.compareTo(other);
            }

        });
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof ComplexNumber)) {
            return false;
        } else {
            ComplexNumber other = (ComplexNumber) o;
            return (Double.compare(real, other.getRe()) == 0)
                    && (Double.compare(im, other.getIm()) == 0);
        }
    }

    @Override
    public String toString() {
        if (this.im == 0) {
            return String.valueOf(this.real);
        } else if (this.real == 0) {
            return String.valueOf(this.im) + "i";
        } else if (this.im < 0) {
            return String.valueOf(this.real) + String.valueOf(this.im) + "i";
        } else {
            return String.valueOf(this.real) + "+" +
                    String.valueOf(this.im) + "i";
        }
    }

    @Override
    public ComplexNumber negate() {
        im *= (-1);
        real *= (-1);
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber other) {
        real += other.getRe();
        im += other.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber other) {
        double prev = real;
        real = real * other.getRe() - im * other.getIm();
        im = prev * other.getIm() + im * other.getRe();
        return this;
    }
}
