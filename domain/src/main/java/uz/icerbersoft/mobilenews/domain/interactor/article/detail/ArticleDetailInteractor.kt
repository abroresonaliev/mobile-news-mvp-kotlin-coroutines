package uz.icerbersoft.mobilenews.domain.interactor.article.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepository
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class ArticleDetailInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun getArticle(articleId: String): Flow<Article> {
        return articleRepository.getArticle(articleId)
            .flowOn(Dispatchers.IO)
    }

    fun updateBookmark(article: Article): Flow<Unit> {
        return articleRepository.updateBookmark(article.articleId, !article.isBookmarked)
            .flowOn(Dispatchers.IO)
    }
}