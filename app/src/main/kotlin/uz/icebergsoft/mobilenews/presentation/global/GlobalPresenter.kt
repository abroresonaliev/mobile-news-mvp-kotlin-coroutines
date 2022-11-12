package uz.icebergsoft.mobilenews.presentation.global

import moxy.MvpPresenter
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import javax.inject.Inject

class GlobalPresenter @Inject constructor(
    private val globalRouter: GlobalRouter
) : MvpPresenter<GlobalView>() {

    override fun onFirstViewAttach() {
        openHomeScreen()
    }

    private fun openHomeScreen(){
        globalRouter.openHomeScreen()
    }
}