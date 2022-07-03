package ru.veprev.android_12.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ru.veprev.android_12.R;
import ru.veprev.android_12.model.CalculatorImplementation;
import ru.veprev.android_12.model.Operator;
import ru.veprev.android_12.presenter.CalculatorPresenter;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private CalculatorPresenter presenter;
    private CalculatorImplementation calculator;
    public static final String USER_INPUT = "USER_INPUT";
    public static final String MEMORY_RESULT = "MEMORY_RESULT";
    private TextView inputView; //для отображения ввода пользователем
    private TextView resultView; //для вывода результата

    private String userInput;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        inputView = findViewById(R.id.calculate_view);
        resultView = findViewById(R.id.result_view);


        if (savedInstanceState != null) {
            userInput = savedInstanceState.getString(USER_INPUT);
            result = savedInstanceState.getString(MEMORY_RESULT);
        }
        inputView.setText(userInput);
        resultView.setText(result);


        presenter = new CalculatorPresenter(this, new CalculatorImplementation());

        //Цифры от 0 до 9
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);

        //Обработка кнопок - цифр
        View.OnClickListener digitClickListener = view -> presenter.onDigitPressed(digits.get(view.getId()));
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);

        //операторы
        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.sum, Operator.SUM);          // операция сложения
        operators.put(R.id.sub, Operator.SUB);          // операция вычитания
        operators.put(R.id.div, Operator.DIV);          // операция деления
        operators.put(R.id.multi, Operator.MULTI);      // операция умножения
        operators.put(R.id.result, Operator.RESULT);    // равно - получение результата операции

        //Обработка кнопок - операторов
        View.OnClickListener operatorClickListener = view -> presenter.onOperatorPressed(operators.get(view.getId()));
        findViewById(R.id.sum).setOnClickListener(operatorClickListener);
        findViewById(R.id.sub).setOnClickListener(operatorClickListener);
        findViewById(R.id.div).setOnClickListener(operatorClickListener);
        findViewById(R.id.multi).setOnClickListener(operatorClickListener);
        findViewById(R.id.result).setOnClickListener(operatorClickListener);

        // операция вычисления процента
        View.OnClickListener percentClickListener = view -> presenter.onPercentPressed();
        findViewById(R.id.percent).setOnClickListener(percentClickListener);

        // операция удаления последнего элемента
        View.OnClickListener delClickListener = view -> presenter.onDelPressed();
        findViewById(R.id.del).setOnClickListener(delClickListener);

        // очищение экрана
        View.OnClickListener clearClickListener = view -> presenter.onClearPressed();
        findViewById(R.id.clear).setOnClickListener(clearClickListener);

        View.OnClickListener doubleZeroClickListener = view -> presenter.onDoubleZeroPressed();
        findViewById(R.id.double_zero).setOnClickListener(doubleZeroClickListener);

        // плавающая точка
        View.OnClickListener dotClickListener = view -> presenter.onDotPressed();
        findViewById(R.id.double_zero).setOnClickListener(dotClickListener);


    }

    /**
     * Метод сохранения результата
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_INPUT, userInput);
        outState.putString(MEMORY_RESULT, result);
    }

    /**
     * Метод вывода результата
     */
    @Override
    public void showResult(String resulting, String calculating) {
        inputView.setText(calculating);
        resultView.setText(result);

        userInput = inputView.getText().toString();
        result = resultView.getText().toString();
    }
}