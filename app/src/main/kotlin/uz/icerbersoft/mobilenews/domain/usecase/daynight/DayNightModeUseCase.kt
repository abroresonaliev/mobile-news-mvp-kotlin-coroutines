package uz.icerbersoft.mobilenews.domain.usecase.daynight

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper

interface DayNightModeUseCase {

    fun getDayNightModWrappers(): Flow<List<DayNightModeWrapper>>

    fun setDayNightMode(dayNightMode: Int)
}