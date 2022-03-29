package uz.icerbersoft.mobilenews.presentation.presentation.home.features.dashboard

import kotlinx.coroutines.flow.*
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticleListUseCase
import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent.*
import javax.inject.Inject

internal class DashboardArticlesPresenter @Inject constructor(
    private val useCase: DashboardArticleListUseCase,
    private val router: GlobalRouter
) : MvpPresenter<DashboardArticlesView>() {

    override fun onFirstViewAttach() {
        getBreakingArticles()
        getTopArticles()
    }

    fun getBreakingArticles() {
        useCase.getBreakingArticles()
            .onStart { viewState.onDefinedBreakingArticleEvents(LoadingState) }
            .onEach {
                if (it.articles.isNotEmpty())
                    viewState.onDefinedBreakingArticleEvents(SuccessState(it.articles))
                else viewState.onDefinedBreakingArticleEvents(EmptyState)
            }
            .catch { viewState.onDefinedBreakingArticleEvents(ErrorState(it.message)) }
            .launchIn(presenterScope)
    }

    fun getTopArticles() {
        flowOf(Unit)
            .debounce(2000)
            .flatMapConcat {
                useCase.getTopArticles()
                    .onStart { viewState.onDefinedTopArticleWrappers(LoadingState) }
                    .onEach { it ->
                        if (it.articles.isNotEmpty())
                            viewState.onDefinedTopArticleWrappers(SuccessState(it.articles))
                        else viewState.onDefinedTopArticleWrappers(EmptyState)
                    }
                    .catch { viewState.onDefinedTopArticleWrappers(ErrorState(it.message)) }
            }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        useCase.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetailScreen(article: Article) =
        router.openArticleDetailScreen(article.articleId)
}