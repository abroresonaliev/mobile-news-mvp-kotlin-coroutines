package uz.icerbersoft.mobilenews.data.datasource.preference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate.*

internal class DayNightModePreference(
    private val sharedPreferences: SharedPreferences
) {
    var dayNightMode: Int = MODE_NIGHT_FOLLOW_SYSTEM
        get() = sharedPreferences.getInt(KEY_DAY_NIGHT_MODE, MODE_NIGHT_FOLLOW_SYSTEM)
            .let {
                when (it) {
                    1 -> MODE_NIGHT_NO
                    2 -> MODE_NIGHT_YES
                    else -> MODE_NIGHT_NO
                }
            }
        set(value) {
            field = when (value) {
                1 -> MODE_NIGHT_NO
                2 -> MODE_NIGHT_YES
                else -> MODE_NIGHT_FOLLOW_SYSTEM
            }
            sharedPreferences.edit().putInt(KEY_DAY_NIGHT_MODE, field).apply()
        }

    fun clear() =
        sharedPreferences.edit().clear().apply()

    private companion object {
        const val KEY_DAY_NIGHT_MODE: String = "int_day_night_mode"
    }
}