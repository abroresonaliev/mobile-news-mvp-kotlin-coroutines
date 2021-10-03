package uz.icerbersoft.mobilenews.app.presentation.detail.router

import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.support.cicerone.base.FeatureRouter
import javax.inject.Inject

class ArticleDetailRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FeatureRouter() {

    fun back() = globalRouter.exit()
}