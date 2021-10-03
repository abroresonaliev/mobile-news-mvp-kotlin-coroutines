package uz.icerbersoft.mobilenews.app.global.di

import dagger.Component
import uz.icerbersoft.mobilenews.app.application.di.ApplicationDaggerComponent
import uz.icerbersoft.mobilenews.app.global.GlobalActivity
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter

@GlobalScope
@Component(
    dependencies = [ApplicationDaggerComponent::class],
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModuleNavigation::class
    ]
)
interface GlobalDaggerComponent : RepositoryProvider {

    val globalRouter: GlobalRouter

    val homeRouter: HomeRouter

    fun inject(activity: GlobalActivity)

    @Component.Factory
    interface Factory {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent
    }

    companion object {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent =
            DaggerGlobalDaggerComponent
                .factory()
                .create(component)
    }
}