package uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import javax.inject.Inject

internal class RecommendedArticlesPresenter @Inject constructor(
    private val useCase: RecommendedArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : MvpPresenter<RecommendedArticlesView>() {

    override fun onFirstViewAttach() =
        getRecommendedArticles()

    fun getRecommendedArticles() {
        useCase.getRecommendedArticles()
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

    fun openArticleDetail(article: Article) {
        globalRouter.openArticleDetailScreen(article.articleId)
    }

    fun back() = homeRouter.openDashboardTab(true)
}