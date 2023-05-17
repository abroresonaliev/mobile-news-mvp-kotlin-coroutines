package uz.icebergsoft.mobilenews.data.datasource.network.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListResponse

internal interface ArticleRestService {

    @GET(API_TOP_HEADINGS)
    fun getBreakingArticles(
        @Query(FIELD_COUNTRY) country: String = "us",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    @GET(API_TOP_HEADINGS)
    fun getTopArticles(
        @Query(FIELD_COUNTRY) country: String = "us",
        @Query(FIELD_CATEGORY) category: String = "business",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    @GET(API_TOP_HEADINGS)
    fun getRecommendedArticles(
        @Query(FIELD_COUNTRY) country: String = "us",
        @Query(FIELD_CATEGORY) category: String = "science",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    private companion object {
        const val API_TOP_HEADINGS: String = "top-headlines"

        const val FIELD_COUNTRY: String = "country"
        const val FIELD_CATEGORY: String = "category"
        const val FIELD_SORT: String = "sortBy"
    }
}