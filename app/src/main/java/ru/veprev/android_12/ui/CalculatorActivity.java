package ru.veprev.android_12.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ru.veprev.android_12.R;
import ru.veprev.android_12.model.CalculatorImplementation;
import ru.veprev.android_12.model.Operator;
import ru.veprev.android_12.model.Theme;
import ru.veprev.android_12.model.ThemeRepository;
import ru.veprev.android_12.model.ThemeRepositoryImpl;
import ru.veprev.android_12.presenter.CalculatorPresenter;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private CalculatorPresenter presenter;
    private CalculatorImplementation calculator;
    private ThemeRepository themeRepository;
    public static final String MEMORY_RESULT = "MEMORY_RESULT";
    private TextView resultView; //для вывода результата
    public static final String EXTRA_THEME = "EXTRA_THEME";

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());
        setContentView(R.layout.activity_calculator);

        resultView = findViewById(R.id.result_view);
        if (savedInstanceState != null)
            result = savedInstanceState.getString(MEMORY_RESULT);

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
        operators.put(R.id.pow, Operator.POW);          // операция возведения в степень
        operators.put(R.id.result, Operator.RESULT);    // равно - получение результата операции

        //Обработка кнопок - операторов
        View.OnClickListener operatorClickListener = view -> presenter.onOperatorPressed(operators.get(view.getId()));
        findViewById(R.id.sum).setOnClickListener(operatorClickListener);
        findViewById(R.id.sub).setOnClickListener(operatorClickListener);
        findViewById(R.id.div).setOnClickListener(operatorClickListener);
        findViewById(R.id.multi).setOnClickListener(operatorClickListener);
        findViewById(R.id.pow).setOnClickListener(operatorClickListener);
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

        // плавающая точка
        View.OnClickListener dotClickListener = view -> presenter.onDotPressed();
        findViewById(R.id.dot).setOnClickListener(dotClickListener);

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                if (intent != null) {
                    Theme selectedOne = (Theme) intent.getSerializableExtra(EXTRA_THEME);
                    themeRepository.saveTheme(selectedOne);
                    recreate();
                }


            }
        });
        //Настройки
        findViewById(R.id.settings).setOnClickListener(view -> {
            Intent intent = new Intent(CalculatorActivity.this, SettingsActivity.class);
            intent.putExtra(EXTRA_THEME, themeRepository.getSavedTheme());

            themeLauncher.launch(intent);
        });

    }

    /**
     * Метод сохранения результата
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MEMORY_RESULT, result);
    }

    /**
     * Метод вывода результата
     */
    @Override
    public void showResult(String resulting) {

        resultView.setText(resulting);
        result = resultView.getText().toString();
    }

}