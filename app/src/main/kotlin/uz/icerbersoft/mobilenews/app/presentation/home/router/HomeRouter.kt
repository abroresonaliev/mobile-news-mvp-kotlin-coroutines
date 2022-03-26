package uz.icerbersoft.mobilenews.app.presentation.home.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.DashboardArticlesFragment
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.ReadLaterArticlesFragment
import uz.icerbersoft.mobilenews.app.presentation.home.features.recommended.RecommendedArticlesFragment
import uz.icerbersoft.mobilenews.app.support.cicerone.base.FeatureRouter

class HomeRouter : FeatureRouter() {

    private var bottomNavigationRouter: Router? = null
    private var navigationListener: ((tab: HomeTab) -> Unit)? = null

    fun setRouter(router: Router) {
        this.bottomNavigationRouter = router
    }

    fun setNavigationListener(listener: (tab: HomeTab) -> Unit) {
        navigationListener = listener
    }

    fun openDashboardTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.DashboardTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.DashboardTab)
    }

    fun openRecommendedTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.RecommendedTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.RecommendedTab)
    }

    fun openReadLaterTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.ReadLaterTab)
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