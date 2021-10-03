package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.di

import dagger.Component
import uz.icerbersoft.mobilenews.app.global.di.GlobalDaggerComponent
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.ReadLaterArticlesFragment

@ReadLaterArticlesDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [ReadLaterArticlesDaggerModule::class]
)
internal interface ReadLaterArticlesDaggerComponent {

    fun inject(fragment: ReadLaterArticlesFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): ReadLaterArticlesDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): ReadLaterArticlesDaggerComponent =
            DaggerReadLaterArticlesDaggerComponent
                .factory()
                .create(component)
    }
}