package uz.icerbersoft.mobilenews.domain.interactor.article.list

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.data.model.article.ArticleListWrapper
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepository
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class ArticleListInteractor @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    fun getBreakingArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getBreakingNewsArticles()
            .flowOn(Dispatchers.IO)
    }

    fun getTopArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getTopArticles()
            .flowOn(Dispatchers.IO)
    }

    fun getRecommendedArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getRecommendedArticles()
            .flowOn(Dispatchers.IO)
    }

    fun getReadLaterArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getReadLaterArticles()
            .flowOn(Dispatchers.IO)
    }

    fun updateBookmark(article: Article): Flow<Unit> {
        return articleRepository.updateBookmark(article.articleId, !article.isBookmarked)
            .flowOn(Dispatchers.IO)
    }
}