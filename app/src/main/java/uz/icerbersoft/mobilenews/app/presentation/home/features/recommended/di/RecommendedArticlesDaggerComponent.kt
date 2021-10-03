package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended.di

import dagger.Component
import uz.icerbersoft.mobilenews.app.global.di.GlobalDaggerComponent
import uz.icerbersoft.mobilenews.app.presentation.home.features.recommended.RecommendedArticlesFragment

@RecommendedArticlesDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [RecommendedArticlesDaggerModule::class]
)
internal interface RecommendedArticlesDaggerComponent {

    fun inject(fragment: RecommendedArticlesFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): RecommendedArticlesDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): RecommendedArticlesDaggerComponent =
            DaggerRecommendedArticlesDaggerComponent
                .factory()
                .create(component)
    }
}