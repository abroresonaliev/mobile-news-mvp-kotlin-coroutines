package uz.icerbersoft.mobilenews.presentation.presentation.home.features.readlater

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.usecase.article.list.ArticleListUseCase
import javax.inject.Inject

internal class ReadLaterArticlesPresenter @Inject constructor(
    private val useCase: ArticleListUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : MvpPresenter<ReadLaterArticlesView>() {

    override fun onFirstViewAttach() =
        getReadLaterArticles()

    fun getReadLaterArticles() {
        useCase.getReadLaterArticles()
            .onStart { viewState.onSuccessArticles(LoadingListEvent.LoadingState) }
            .onEach {
                if (it.articles.isNotEmpty())
                    viewState.onSuccessArticles(LoadingListEvent.SuccessState(it.articles))
                else viewState.onSuccessArticles(LoadingListEvent.EmptyState)
            }
            .catch { viewState.onSuccessArticles(LoadingListEvent.ErrorState(it.message)) }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        useCase.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetailScreen(article: Article) {
        globalRouter.openArticleDetailScreen(article.articleId)
    }

    fun back() {
        homeRouter.openDashboardTab(true)
    }
}