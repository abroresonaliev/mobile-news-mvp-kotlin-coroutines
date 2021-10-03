package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.di

import dagger.Component
import uz.icerbersoft.mobilenews.app.global.di.GlobalDaggerComponent
import uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.DashboardArticlesFragment

@DashboardArticlesDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [DashboardArticlesDaggerModule::class]
)
internal interface DashboardArticlesDaggerComponent {

    fun inject(fragment: DashboardArticlesFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): DashboardArticlesDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): DashboardArticlesDaggerComponent =
            DaggerDashboardArticlesDaggerComponent
                .factory()
                .create(component)
    }
}