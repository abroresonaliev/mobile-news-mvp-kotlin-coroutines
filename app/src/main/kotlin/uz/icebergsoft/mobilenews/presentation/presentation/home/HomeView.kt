package uz.icebergsoft.mobilenews.presentation.presentation.home

import moxy.MvpView
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter

interface HomeView : MvpView {

    fun onTabChanged(tab: HomeRouter.HomeTab)
}