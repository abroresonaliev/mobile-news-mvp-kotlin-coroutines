package uz.icerbersoft.mobilenews.data.repository.article

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.data.model.article.ArticleListWrapper

interface ArticleRepository {

    fun getArticle(articleId: String): Flow<Article>

    fun getArticles(): Flow<ArticleListWrapper>

    fun getBreakingNewsArticles(): Flow<ArticleListWrapper>

    fun getTopArticles(): Flow<ArticleListWrapper>

    fun getRecommendedArticles(): Flow<ArticleListWrapper>

    fun getReadLaterArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(articleId: String, isBookmarked: Boolean): Flow<Unit>
}