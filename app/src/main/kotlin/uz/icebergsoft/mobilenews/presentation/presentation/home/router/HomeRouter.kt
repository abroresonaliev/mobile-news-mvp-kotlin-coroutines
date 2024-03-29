package uz.icebergsoft.mobilenews.presentation.presentation.home.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard.DashboardArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater.ReadLaterArticlesFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended.RecommendedArticlesFragment
import uz.icebergsoft.mobilenews.presentation.support.cicerone.router.BaseFeatureRouter

class HomeRouter : BaseFeatureRouter() {

    private var bottomNavigationRouter: Router? = null
    private var navigationListener: ((tab: HomeTab) -> Unit)? = null

    fun setRouter(router: Router) {
        this.bottomNavigationRouter = router
    }

    fun setNavigationListener(listener: (tab: HomeTab) -> Unit) {
        navigationListener = listener
    }

    fun openDashboardTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.navigateTo(Tabs.DashboardTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.DashboardTab)
    }

    fun openRecommendedTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.navigateTo(Tabs.RecommendedTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.RecommendedTab)
    }

    fun openReadLaterTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.navigateTo(Tabs.ReadLaterTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.ReadLaterTab)
    }

    private object Tabs {

        object DashboardTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                DashboardArticlesFragment.newInstance()
        }

        object RecommendedTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                RecommendedArticlesFragment.newInstance()
        }

        object ReadLaterTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                ReadLaterArticlesFragment.newInstance()
        }
    }

    sealed class HomeTab {
        object DashboardTab : HomeTab()
        object RecommendedTab : HomeTab()
        object ReadLaterTab : HomeTab()
    }
}