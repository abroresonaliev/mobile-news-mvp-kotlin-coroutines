package uz.icerbersoft.mobilenews.domain.usecase.article.list

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import javax.inject.Inject

class ArticleListUseCase @Inject constructor(
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