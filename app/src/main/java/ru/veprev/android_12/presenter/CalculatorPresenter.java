package ru.veprev.android_12.presenter;




import java.text.DecimalFormat;

import ru.veprev.android_12.model.Calculator;
import ru.veprev.android_12.model.Operator;
import ru.veprev.android_12.ui.CalculatorView;

public class CalculatorPresenter {
    private CalculatorView calculatorView;
    private Calculator calculator;


    private double argOne;
    private Double argTwo;
    private int lastDigit;
    private Operator selectedOperator;

    private final DecimalFormat formatter = new DecimalFormat("#.##");

    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculatorView = calculatorView;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }
    }

    public void onOperatorPressed(Operator operator) {
        if (selectedOperator != null && selectedOperator != Operator.RESULT) {
            argOne = calculator.performOperation(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
    }

    public void onPercentPressed() {
        argOne = argOne / 100;
        showFormatted(argOne);
    }

    public void onDelPressed() {
        if (argTwo == null) {
            lastDigit = (int)argOne / 10;
            argOne = lastDigit;
            showFormatted(argOne);
        } else {
            lastDigit = (int)(argTwo / 10);
            argTwo = (double)lastDigit;
            showFormatted(argTwo);
        }
    }

    public void onClearPressed() {
        argOne = 0.0;
        argTwo = null;
        selectedOperator = null;
        calculatorView.showResult("");
    }

    public void onDotPressed() {
        if (argTwo == null) {
            argOne = argOne * Math.pow(10, -1) ;
            showFormatted(argOne);
        }
    }

    private void showFormatted(double value) {
        calculatorView.showResult(formatter.format(value));
    }


}
