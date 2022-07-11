package ru.veprev.android_12.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ru.veprev.android_12.R;

public enum Theme {
    DARK(R.style.Theme_Android_12_DarkTheme, R.string.dark_theme, "themeDark"),
    LIGHT(R.style.Theme_Android_12_LightTheme, R.string.light_theme, "themeLight");

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;

    private String key;

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }
}
