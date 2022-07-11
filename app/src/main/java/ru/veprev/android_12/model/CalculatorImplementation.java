package ru.veprev.android_12.model;

public class CalculatorImplementation implements Calculator {
    @Override
    public double performOperation(double arg1, double arg2, Operator operator) {
        switch (operator) {
            case SUM:
                return arg1 + arg2;
            case SUB:
                return arg1 - arg2;
            case MULTI:
                return arg1 * arg2;
            case DIV:
                return arg1 / arg2;
            case POW:
                return Math.pow(arg1, arg2);
        }
        return 0.0;
    }
}
