package uz.icerbersoft.mobilenews.app.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.icerbersoft.mobilenews.app.application.di.ApplicationDaggerModule.Provider
import uz.icerbersoft.mobilenews.data.provider.DataProvider
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider
import javax.inject.Singleton

@Module(includes = [Provider::class])
object ApplicationDaggerModule {

    @Module
    object Provider {

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataProvider(
            context: Context
        ): DataProvider = DataProvider(context)

        @JvmStatic
        @Provides
        @Singleton
        fun provideRepositoryProvider(
            dataProvider: DataProvider
        ): RepositoryProvider = dataProvider.repositoryProvider
    }
}