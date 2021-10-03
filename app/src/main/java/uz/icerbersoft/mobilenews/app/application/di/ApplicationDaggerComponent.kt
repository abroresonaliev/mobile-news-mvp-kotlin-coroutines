package uz.icerbersoft.mobilenews.app.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.icerbersoft.mobilenews.app.application.Application
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDaggerModule::class, ApplicationDaggerModuleRepository::class])
interface ApplicationDaggerComponent : RepositoryProvider {

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationDaggerComponent
    }

    companion object {
        fun create(context: Context): ApplicationDaggerComponent =
            DaggerApplicationDaggerComponent
                .factory()
                .create(context)
    }
}