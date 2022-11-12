package uz.icerbersoft.mobilenews.presentation.application.manager.daynight

import uz.icerbersoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icerbersoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import uz.icerbersoft.mobilenews.presentation.utils.convertToDayNightMode

internal class DayNightModeManager(
    private val dayNightModePreference: DayNightModePreference
) {

    fun getDayNightMode(): Int {
        return dayNightModePreference.dayNightMode.convertToAppDelegateModeNight()
    }

    fun setDayNightMode(dayNightMode: Int) {
        dayNightModePreference.dayNightMode = dayNightMode.convertToDayNightMode()
    }
}