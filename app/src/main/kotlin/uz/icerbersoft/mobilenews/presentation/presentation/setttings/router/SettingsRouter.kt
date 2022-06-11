package uz.icerbersoft.mobilenews.presentation.presentation.setttings.router

import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.support.cicerone.base.BaseFeatureRouter
import javax.inject.Inject

class SettingsRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : BaseFeatureRouter() {

    fun back() = globalRouter.exit()
}