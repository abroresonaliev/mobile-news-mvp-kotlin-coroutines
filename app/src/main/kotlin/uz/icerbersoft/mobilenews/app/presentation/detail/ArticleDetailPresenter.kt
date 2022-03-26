package uz.icerbersoft.mobilenews.app.presentation.detail

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.app.presentation.detail.router.ArticleDetailRouter
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.app.usecase.article.detail.ArticleDetailUseCase
import javax.inject.Inject
import kotlin.properties.Delegates

class ArticleDetailPresenter @Inject constructor(
    private val useCase: ArticleDetailUseCase,
    private val router: ArticleDetailRouter
) : MvpPresenter<ArticleDetailView>() {

    private var currentArticleId: String by Delegates.notNull()

    fun setArticleId(value: String) {
        currentArticleId = value
    }

    override fun onFirstViewAttach() =
        getArticleDetail()

    fun getArticleDetail() {
        useCase.getArticle(currentArticleId)
            .onEach { viewState.onSuccessArticleDetail(it) }
            .launchIn(presenterScope)
    }

    fun updateBookmark(article: Article) {
        presenterScope.launch {
            useCase
                .updateBookmark(article)
                .collect { }
        }
    }

    fun back() = router.back()
}