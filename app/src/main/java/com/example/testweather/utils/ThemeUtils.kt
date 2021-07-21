package com.example.testweather.utils

import androidx.appcompat.app.AppCompatDelegate
import com.example.testweather.utils.Theme.LIGHT
import com.example.testweather.utils.Theme.NIGHT

object ThemeUtils {
    fun setAppTheme(theme: Theme) {
        when (theme) {
            LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}