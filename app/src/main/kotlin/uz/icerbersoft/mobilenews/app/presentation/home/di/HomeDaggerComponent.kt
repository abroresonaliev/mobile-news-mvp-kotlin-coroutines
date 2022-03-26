package uz.icerbersoft.mobilenews.app.presentation.home.di

import dagger.Component
import uz.icerbersoft.mobilenews.app.global.di.GlobalDaggerComponent
import uz.icerbersoft.mobilenews.app.presentation.home.HomeFragment
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider

@HomeDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [HomeDaggerModule::class]
)
internal interface HomeDaggerComponent : RepositoryProvider {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): HomeDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): HomeDaggerComponent =
            DaggerHomeDaggerComponent
                .factory()
                .create(component)
    }
}