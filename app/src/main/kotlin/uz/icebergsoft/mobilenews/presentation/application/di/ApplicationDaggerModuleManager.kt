package uz.icebergsoft.mobilenews.presentation.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.icebergsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebergsoft.mobilenews.presentation.application.manager.daynight.DayNightModeManager
import uz.icebergsoft.mobilenews.presentation.application.manager.resource.ResourceManager
import javax.inject.Singleton

@Module
internal object ApplicationDaggerModuleManager {

    @JvmStatic
    @Provides
    @Singleton
    fun dayNightModeManager(
        dayNightModePreference: DayNightModePreference
    ): DayNightModeManager = DayNightModeManager(dayNightModePreference)

    @JvmStatic
    @Provides
    @Singleton
    fun resourceManager(
        context: Context
    ): ResourceManager = ResourceManager(context)
}