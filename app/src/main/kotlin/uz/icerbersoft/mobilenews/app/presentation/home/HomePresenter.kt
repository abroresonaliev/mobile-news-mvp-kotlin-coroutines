package uz.icerbersoft.mobilenews.app.presentation.home

import moxy.MvpPresenter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val router: HomeRouter
) : MvpPresenter<HomeView>() {

    override fun onFirstViewAttach() {
        router.setNavigationListener { viewState.onTabChanged(it) }
        openDashboardTab()
    }

    fun openDashboardTab() {
        router.openDashboardTab()
    }

    fun openRecommendedTab() {
        router.openRecommendedTab()
    }

    fun openReadLaterTab() {
        router.openReadLaterTab()
    }
}