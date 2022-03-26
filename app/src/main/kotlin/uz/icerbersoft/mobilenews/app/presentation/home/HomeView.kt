package uz.icerbersoft.mobilenews.app.presentation.home

import moxy.MvpView
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter

interface HomeView : MvpView {

    fun onTabChanged(tab: HomeRouter.HomeTab)
}