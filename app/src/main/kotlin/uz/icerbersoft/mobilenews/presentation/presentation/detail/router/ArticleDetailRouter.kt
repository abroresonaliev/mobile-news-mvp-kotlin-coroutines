package uz.icerbersoft.mobilenews.presentation.presentation.detail.router

import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.support.cicerone.base.FeatureRouter
import javax.inject.Inject

class ArticleDetailRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FeatureRouter() {

    fun back() = globalRouter.exit()
}