package uz.icebergsoft.mobilenews.domain.data.repository.article

import kotlinx.coroutines.flow.Flow
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.PaginationData

interface ArticleRepository {

    fun getArticle(articleId: String): Flow<Article>

    fun getArticles(): Flow<ArticleListWrapper>

    fun getBreakingNewsArticles(): Flow<ArticleListWrapper>

    fun getTopArticles(): Flow<ArticleListWrapper>

    fun getRecommendedArticles(page: Int): Flow<PaginationData<Article>>

    fun getReadLaterArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(articleId: String, isBookmarked: Boolean): Flow<Unit>
}