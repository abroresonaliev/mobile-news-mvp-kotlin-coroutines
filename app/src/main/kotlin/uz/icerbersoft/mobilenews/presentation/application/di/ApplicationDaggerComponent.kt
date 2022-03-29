package uz.icerbersoft.mobilenews.presentation.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.icerbersoft.mobilenews.presentation.application.Application
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModuleDataSource
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModuleRepository
import uz.icerbersoft.mobilenews.presentation.application.di.domain.DomainDaggerModuleUseCase
import uz.icerbersoft.mobilenews.presentation.global.di.GlobalDaggerComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationDaggerModule::class,
        ApplicationDaggerModuleSubComponents::class,
        DataDaggerModuleDataSource::class,
        DataDaggerModuleRepository::class,
        DomainDaggerModuleUseCase::class
    ]
)
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