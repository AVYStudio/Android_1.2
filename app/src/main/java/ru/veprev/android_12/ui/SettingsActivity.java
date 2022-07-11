package ru.veprev.android_12.ui;

import static ru.veprev.android_12.ui.CalculatorActivity.EXTRA_THEME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.veprev.android_12.R;
import ru.veprev.android_12.model.Theme;
import ru.veprev.android_12.model.ThemeRepository;
import ru.veprev.android_12.model.ThemeRepositoryImpl;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ThemeRepository themeRepository = ThemeRepositoryImpl.getInstance(this);

        List<Theme> themes = themeRepository.getAllThemes();

        LinearLayout container = findViewById(R.id.container);

        Intent intent = getIntent();

        Theme selectedTheme = (Theme) intent.getSerializableExtra(EXTRA_THEME);

        for (Theme theme: themes) {
            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);

            container.addView(itemView);

            TextView title  = itemView.findViewById(R.id.title);
            title.setText(theme.getTitle());

            ImageView check = itemView.findViewById(R.id.check);
            if (theme.equals(selectedTheme)) {
                check.setVisibility(View.VISIBLE);
            } else {
                check.setVisibility(View.GONE);
            }

            CardView card = itemView.findViewById(R.id.card_view);
            card.setOnClickListener(view -> {
                Intent data = new Intent();
                data.putExtra(EXTRA_THEME, theme);

                setResult(Activity.RESULT_OK, data);
                finish();
            });


        }


    }
}