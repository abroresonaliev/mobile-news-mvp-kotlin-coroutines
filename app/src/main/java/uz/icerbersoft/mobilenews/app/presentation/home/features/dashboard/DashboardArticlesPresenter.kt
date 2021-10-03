package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper.*
import uz.icerbersoft.mobilenews.domain.interactor.article.list.ArticleListInteractor
import javax.inject.Inject

internal class DashboardArticlesPresenter @Inject constructor(
    private val interactor: ArticleListInteractor,
    private val router: GlobalRouter
) : MvpPresenter<DashboardArticlesView>() {

    override fun onFirstViewAttach() {
        getBreakingArticles()
        getTopArticles()
    }

    fun getBreakingArticles() {
        interactor.getBreakingArticles()
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
        interactor.getTopArticles()
            .onStart { viewState.onDefinedTopArticleWrappers(listOf(LoadingItem)) }
            .catch { viewState.onDefinedTopArticleWrappers(listOf(ErrorItem)) }
            .onEach { it ->
                if (it.articles.isNotEmpty())
                    viewState.onDefinedTopArticleWrappers(it.articles.map { ArticleItem(it) })
                else viewState.onDefinedTopArticleWrappers(listOf(EmptyItem))
            }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        interactor.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetailScreen(article: Article) =
        router.openArticleDetailScreen(article.articleId)
}