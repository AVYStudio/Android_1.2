package ru.veprev.android_12.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository {

    private SharedPreferences sharedPreferences;
    public static final String KEY_THEME = "KEY_THEME";
    private static ThemeRepository INSTANCE;

    public static ThemeRepository getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new ThemeRepositoryImpl(context);

        return INSTANCE;
    }

    public ThemeRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences("themes", Context.MODE_PRIVATE);

    }

    @Override
    public Theme getSavedTheme() {
        String savedKey = sharedPreferences.getString(KEY_THEME, Theme.LIGHT.getKey());

        for (Theme theme: Theme.values()) {
            if (theme.getKey().equals(savedKey))
                return theme;
        }
        return Theme.LIGHT;
    }

    @Override
    public void saveTheme(Theme theme) {
        sharedPreferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();

    }

    @Override
    public List<Theme> getAllThemes() {
        return Arrays.asList(Theme.values());
    }

}
