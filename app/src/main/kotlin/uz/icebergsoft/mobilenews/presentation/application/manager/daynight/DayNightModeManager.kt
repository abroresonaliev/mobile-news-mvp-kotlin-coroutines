package uz.icebergsoft.mobilenews.presentation.application.manager.daynight

import uz.icebergsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebergsoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import uz.icebergsoft.mobilenews.presentation.utils.convertToDayNightMode

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