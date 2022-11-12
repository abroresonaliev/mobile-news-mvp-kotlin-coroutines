package uz.icebergsoft.mobilenews.domain.usecase.daynight

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.settings.SettingsRepository
import uz.icebergsoft.mobilenews.presentation.utils.convertToDayNightMode
import javax.inject.Inject

class DayNightModeUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : DayNightModeUseCase {

    override fun getDayNightModWrappers(): Flow<List<DayNightModeWrapper>> {
        return settingsRepository.getSelectedDayNightMode()
            .flatMapConcat { dayNightMode ->
                settingsRepository.getDayNightModes()
                    .map { it -> it.map { DayNightModeWrapper(it, it == dayNightMode) } }
            }
    }

    override fun setDayNightMode(dayNightMode: Int) {
        settingsRepository.saveDayNightMode(dayNightMode.convertToDayNightMode())
    }
}