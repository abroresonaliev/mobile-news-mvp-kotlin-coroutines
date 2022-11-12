package uz.icebergsoft.mobilenews.presentation.application.di.data

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import uz.icebergsoft.mobilenews.data.datasource.preference.DayNightModePreference
import javax.inject.Singleton

@Module
internal object DataDaggerModulePreference {

    @JvmStatic
    @Provides
    @Singleton
    fun dayNightModePreference(
        context: Context
    ): DayNightModePreference =
        DayNightModePreference(getSharedPreference(context, "preference_day_night_mode"))

    private fun getSharedPreference(context: Context, preferenceName: String): SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
}