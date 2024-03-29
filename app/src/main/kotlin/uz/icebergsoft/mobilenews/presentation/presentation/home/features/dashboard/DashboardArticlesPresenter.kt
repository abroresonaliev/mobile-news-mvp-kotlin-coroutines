package uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard

import android.util.Log
import kotlinx.coroutines.flow.*
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import javax.inject.Inject

internal class DashboardArticlesPresenter @Inject constructor(
    private val useCase: DashboardArticlesUseCase,
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
            .catch {
                Log.wtf("", "getBreakingArticles() error = $it")
                viewState.onDefinedBreakingArticleEvents(ErrorState(it.message))
            }
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
                    .catch {
                        Log.wtf("", "getTopArticles() error = $it")
                        viewState.onDefinedTopArticleWrappers(ErrorState(it.message))
                    }
            }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        useCase.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetailScreen(article: Article) =
        router.openArticleDetailScreen(article.articleId)

    fun openSettingsScreen() =
        router.openSettingsScreen()
}