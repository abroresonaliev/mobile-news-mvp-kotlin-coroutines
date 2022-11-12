package uz.icebergsoft.mobilenews.presentation.utils

import androidx.appcompat.app.AppCompatDelegate
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightMode

fun DayNightMode.convertToAppDelegateModeNight(): Int =
    when (this) {
        DayNightMode.ONLY_LIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_NO
        DayNightMode.ONLY_NIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_YES
        DayNightMode.FOLLOW_SYSTEM_NIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

fun Int.convertToDayNightMode(): DayNightMode =
    when (this) {
        1 -> DayNightMode.ONLY_LIGHT_MODE
        2 -> DayNightMode.ONLY_NIGHT_MODE
        -1 -> DayNightMode.FOLLOW_SYSTEM_NIGHT_MODE
        else -> DayNightMode.DEFAULT
    }