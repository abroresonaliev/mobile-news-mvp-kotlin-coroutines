package uz.icebergsoft.mobilenews.presentation.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.icebergsoft.mobilenews.presentation.application.Application
import uz.icebergsoft.mobilenews.presentation.global.di.GlobalDaggerComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDaggerModule::class])
internal interface ApplicationDaggerComponent {

    fun inject(application: Application)

    val globalDaggerComponent: GlobalDaggerComponent.Factory

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