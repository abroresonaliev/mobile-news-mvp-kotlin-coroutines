package uz.icebergsoft.mobilenews.presentation.global.di

import dagger.Subcomponent
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.detail.ArticleDetailFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.di.HomeDaggerComponent
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard.DashboardArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater.ReadLaterArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended.RecommendedArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebergsoft.mobilenews.presentation.presentation.readcollection.ReadCollectionArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.setttings.SettingsFragment

@GlobalScope
@Subcomponent(
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModuleNavigation::class,
        GlobalDaggerModuleSubComponents::class
    ]
)
internal interface GlobalDaggerComponent {

    val globalRouter: GlobalRouter
    val homeRouter: HomeRouter

    val homeDaggerComponent: HomeDaggerComponent.Factory

    fun inject(activity: GlobalActivity)

    fun inject(fragment: ArticleDetailFragment)

    fun inject(fragment: DashboardArticlesFragment)

    fun inject(fragment: ReadCollectionArticlesFragment)
    fun inject(fragment: ReadLaterArticlesFragment)
    fun inject(fragment: RecommendedArticlesFragment)

    fun inject(fragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): GlobalDaggerComponent
    }
}