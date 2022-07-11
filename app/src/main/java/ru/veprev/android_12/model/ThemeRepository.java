package ru.veprev.android_12.model;

import java.util.List;

public interface ThemeRepository {

    Theme getSavedTheme();

    void saveTheme(Theme theme);

    List<Theme> getAllThemes();
}
