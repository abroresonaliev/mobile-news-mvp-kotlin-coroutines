package uz.icebergsoft.mobilenews.data.datasource.preference

import android.content.SharedPreferences
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightMode

internal class DayNightModePreference(
    private val sharedPreferences: SharedPreferences
) {
    var dayNightMode: DayNightMode = DayNightMode.DEFAULT
        get() = sharedPreferences.getInt(KEY_DAY_NIGHT_MODE, DayNightMode.DEFAULT.modeIndex)
            .let {
                when (it) {
                    1 -> DayNightMode.ONLY_LIGHT_MODE
                    2 -> DayNightMode.ONLY_NIGHT_MODE
                    else -> DayNightMode.FOLLOW_SYSTEM_NIGHT_MODE
                }
            }
        set(value) {
            field = when (value.modeIndex) {
                1 -> DayNightMode.ONLY_LIGHT_MODE
                2 -> DayNightMode.ONLY_NIGHT_MODE
                else -> DayNightMode.FOLLOW_SYSTEM_NIGHT_MODE
            }
            sharedPreferences.edit().putInt(KEY_DAY_NIGHT_MODE, field.modeIndex).apply()
        }

    fun clear() =
        sharedPreferences.edit().clear().apply()

    private companion object {
        const val KEY_DAY_NIGHT_MODE: String = "int_day_night_mode"
    }
}