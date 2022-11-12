package uz.icebergsoft.mobilenews.presentation.presentation.detail.router

import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.support.cicerone.router.BaseFeatureRouter
import javax.inject.Inject

class ArticleDetailRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : BaseFeatureRouter() {

    fun back() = globalRouter.exit()
}