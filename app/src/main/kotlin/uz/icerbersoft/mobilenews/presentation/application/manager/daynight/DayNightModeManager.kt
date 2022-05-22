package uz.icerbersoft.mobilenews.presentation.application.manager.daynight

import uz.icerbersoft.mobilenews.data.datasource.preference.DayNightModePreference

internal class DayNightModeManager(
    private val dayNightModePreference: DayNightModePreference
) {

    fun getDayNightMode(): Int {
        return dayNightModePreference.dayNightMode
    }

    fun setDayNightMode(dayNightMode: Int) {
        dayNightModePreference.dayNightMode = dayNightMode
    }
}