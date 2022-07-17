package uz.icerbersoft.mobilenews.presentation.presentation.home.features.recommended

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.easyadapter.pagination.PaginationState.*
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icerbersoft.mobilenews.domain.utils.state
import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent.*
import javax.inject.Inject

internal class RecommendedArticlesPresenter @Inject constructor(
    private val useCase: RecommendedArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : MvpPresenter<RecommendedArticlesView>() {

    private val articles: DataList<Article> = DataList.empty()
    private var state: PaginationState = COMPLETE

    override fun onFirstViewAttach() =
        getRecommendedArticles()

    fun getRecommendedArticles() {
        useCase.getRecommendedArticles()
            .onStart {
                if (state != READY)
                    viewState.onDefinedArticles(LoadingState, COMPLETE)
            }
            .onEach {
                articles.merge(it)
                state = articles.state

                if (articles.isNotEmpty())
                    viewState.onDefinedArticles(SuccessState(articles), state)
                else viewState.onDefinedArticles(EmptyState, state)
            }
            .catch {
                if (state == READY) {
                    viewState.onDefinedArticles(SuccessState(articles), ERROR)
                } else
                    viewState.onDefinedArticles(ErrorState(it.localizedMessage), COMPLETE)
            }
            .launchIn(presenterScope)
    }

    fun loadNextPage() {
        useCase.loadNextPage()
        getRecommendedArticles()
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