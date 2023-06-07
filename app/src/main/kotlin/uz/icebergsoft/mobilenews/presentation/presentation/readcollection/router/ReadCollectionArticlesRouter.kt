package uz.icebergsoft.mobilenews.presentation.presentation.readcollection.router

import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.support.cicerone.router.BaseFeatureRouter
import javax.inject.Inject

class ReadCollectionArticlesRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : BaseFeatureRouter() {

    fun openArticleDetailScreen(articleId: String) =
        globalRouter.openArticleDetailScreen(articleId)

    fun back() =
        globalRouter.exit()
}