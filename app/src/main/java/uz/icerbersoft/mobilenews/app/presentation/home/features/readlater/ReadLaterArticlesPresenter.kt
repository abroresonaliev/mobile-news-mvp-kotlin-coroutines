package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper.*
import uz.icerbersoft.mobilenews.domain.interactor.article.list.ArticleListInteractor
import javax.inject.Inject

internal class ReadLaterArticlesPresenter @Inject constructor(
    private val interactor: ArticleListInteractor,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : MvpPresenter<ReadLaterArticlesView>() {

    override fun onFirstViewAttach() =
        getReadLaterArticles()

    fun getReadLaterArticles() {
        interactor.getReadLaterArticles()
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
        interactor.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetailScreen(article: Article) {
        globalRouter.openArticleDetailScreen(article.articleId)
    }

    fun back() {
        homeRouter.openDashboardTab(true)
    }
}