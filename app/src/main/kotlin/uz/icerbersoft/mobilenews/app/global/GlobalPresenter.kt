package uz.icerbersoft.mobilenews.app.global

import moxy.MvpPresenter
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import javax.inject.Inject

class GlobalPresenter @Inject constructor(
    private val globalRouter: GlobalRouter
) : MvpPresenter<GlobalView>() {

    fun onActivityCreate() {
        globalRouter.openHomeScreen()
    }
}