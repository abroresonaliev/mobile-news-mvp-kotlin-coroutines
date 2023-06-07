package uz.icebergsoft.mobilenews.domain.data.repository.article

import kotlinx.coroutines.flow.Flow
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.PaginationData

interface ArticleRepository {

    fun getArticle(articleId: String): Flow<Article>

    fun getBreakingNewsArticles(
        country: String = "us",
        category: String = "world",
        sortBy: String = "popularity"
    ): Flow<ArticleListWrapper>

    fun getTopArticles(
        country: String = "us",
        category: String = "general",
        sortBy: String = "popularity"
    ): Flow<ArticleListWrapper>

    fun getRecommendedArticles(
        country: String = "us",
        category: String = "science",
        sortBy: String = "popularity"
    ): Flow<ArticleListWrapper>

    fun getSavedBreakingArticles(page: Int, perPage: Int): Flow<PaginationData<Article>>

    fun getSavedTopArticles(page: Int, perPage: Int): Flow<PaginationData<Article>>

    fun getSavedRecommendedArticles(page: Int, perPage: Int): Flow<PaginationData<Article>>

    fun getSavedReadLaterArticles(page: Int, perPage: Int): Flow<PaginationData<Article>>

    fun updateBookmark(articleId: String, isBookmarked: Boolean): Flow<Unit>
}