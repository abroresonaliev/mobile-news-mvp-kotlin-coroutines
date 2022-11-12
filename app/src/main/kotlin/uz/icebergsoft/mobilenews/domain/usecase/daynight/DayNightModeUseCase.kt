package uz.icebergsoft.mobilenews.domain.usecase.daynight

import kotlinx.coroutines.flow.Flow
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper

interface DayNightModeUseCase {

    fun getDayNightModWrappers(): Flow<List<DayNightModeWrapper>>

    fun setDayNightMode(dayNightMode: Int)
}