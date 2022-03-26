package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.app.usecase.article.detail.model.ArticleWrapper.*
import uz.icerbersoft.mobilenews.app.usecase.article.list.ArticleListUseCase
import javax.inject.Inject

internal class RecommendedArticlesPresenter @Inject constructor(
    private val useCase: ArticleListUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : MvpPresenter<RecommendedArticlesView>() {

    override fun onFirstViewAttach() =
        getRecommendedArticles()

    fun getRecommendedArticles() {
        useCase.getRecommendedArticles()
            .onStart { viewState.onSuccessArticles(listOf(LoadingItem)) }
            .catch { viewState.onSuccessArticles(listOf(ErrorItem)) }
            .onEach { it ->
                if (it.articles.isNotEmpty())
                    viewState.onSuccessArticles(it.articles.map { ArticleItem(it) })
                else viewState.onSuccessArticles(listOf(EmptyItem))
            }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        useCase.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetail(article: Article) {
        globalRouter.openArticleDetailScreen(article.articleId)
    }

    fun back() = homeRouter.openDashboardTab(true)
}