package uz.icerbersoft.mobilenews.data.repository.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.icerbersoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icerbersoft.mobilenews.domain.data.entity.settings.DayNightMode
import uz.icerbersoft.mobilenews.domain.data.repository.settings.SettingsRepository
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dayNightModePreference: DayNightModePreference,
) : SettingsRepository {

    override fun getDayNightModes(): Flow<List<DayNightMode>> {
        return flowOf(DayNightMode.values().toList())
    }

    override fun getSelectedDayNightMode(): Flow<DayNightMode> {
        return flowOf(dayNightModePreference.dayNightMode)
    }

    override fun saveDayNightMode(value: DayNightMode) {
        dayNightModePreference.dayNightMode = value
    }
}