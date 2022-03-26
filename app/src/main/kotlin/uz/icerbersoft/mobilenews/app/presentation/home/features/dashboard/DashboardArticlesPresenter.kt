package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard

import kotlinx.coroutines.flow.*
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.app.usecase.article.detail.model.ArticleWrapper.*
import uz.icerbersoft.mobilenews.app.usecase.article.list.ArticleListUseCase
import javax.inject.Inject

internal class DashboardArticlesPresenter @Inject constructor(
    private val useCase: ArticleListUseCase,
    private val router: GlobalRouter
) : MvpPresenter<DashboardArticlesView>() {

    override fun onFirstViewAttach() {
        getBreakingArticles()
        getTopArticles()
    }

    fun getBreakingArticles() {
        useCase.getBreakingArticles()
            .onStart { viewState.onDefinedBreakingArticleWrappers(listOf(LoadingItem)) }
            .catch { viewState.onDefinedBreakingArticleWrappers(listOf(ErrorItem)) }
            .onEach { it ->
                if (it.articles.isNotEmpty())
                    viewState.onDefinedBreakingArticleWrappers(it.articles.map { ArticleItem(it) })
                else viewState.onDefinedBreakingArticleWrappers(listOf(EmptyItem))
            }
            .launchIn(presenterScope)
    }

    fun getTopArticles() {
        flowOf(Unit)
            .debounce(2000)
            .flatMapConcat {
                useCase.getTopArticles()
                    .onStart { viewState.onDefinedTopArticleWrappers(listOf(LoadingItem)) }
                    .catch { viewState.onDefinedTopArticleWrappers(listOf(ErrorItem)) }
                    .onEach { it ->
                        if (it.articles.isNotEmpty())
                            viewState.onDefinedTopArticleWrappers(it.articles.map { ArticleItem(it) })
                        else viewState.onDefinedTopArticleWrappers(listOf(EmptyItem))
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
}