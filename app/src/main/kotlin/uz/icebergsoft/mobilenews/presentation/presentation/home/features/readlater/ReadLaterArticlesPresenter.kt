package uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import javax.inject.Inject

internal class ReadLaterArticlesPresenter @Inject constructor(
    private val useCase: ReadLaterArticlesUseCase,
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