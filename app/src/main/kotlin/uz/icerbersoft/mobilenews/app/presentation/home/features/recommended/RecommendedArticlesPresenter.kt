package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.domain.usecase.article.list.ArticleListUseCase
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