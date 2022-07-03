package ru.veprev.android_12.presenter;

import ru.veprev.android_12.model.Calculator;
import ru.veprev.android_12.model.Operator;
import ru.veprev.android_12.ui.CalculatorView;

public class CalculatorPresenter {
    private CalculatorView calculatorView;
    private Calculator calculator;

    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculatorView = calculatorView;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
    }

    public void onOperatorPressed(Operator operator) {
    }

    public void onPercentPressed() {
    }

    public void onDelPressed() {
    }

    public void onClearPressed() {
    }

    public void onDoubleZeroPressed() {
    }

    public void onDotPressed() {
    }
}
