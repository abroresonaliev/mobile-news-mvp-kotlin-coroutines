package uz.icerbersoft.mobilenews.data.datasource.rest.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListResponse

internal interface ArticleRestService {

    @GET("${API_TOP_HEADINGS}${API_API_KEY}")
    fun getBreakingArticles(
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    @GET("${API_TOP_HEADINGS}${API_API_KEY}")
    fun getTopArticles(
        @Query(FIELD_COUNTRY) country: String = "us",
        @Query(FIELD_CATEGORY) category: String = "business",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    @GET("${API_TOP_HEADINGS}${API_API_KEY}")
    fun getRecommendedArticles(
        @Query(FIELD_CATEGORY) category: String = "science",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Flow<ArticleListResponse>

    private companion object {
        const val API_ARTICLES: String = "everything"
        const val API_TOP_HEADINGS: String = "top-headlines"

        const val API_API_KEY: String = "?country=us&apiKey=8eca259240354cd1b70a02b5f7185c62"

        const val FIELD_COUNTRY: String = "country"
        const val FIELD_CATEGORY: String = "category"
        const val FIELD_DATE_RANGE_START: String = "from"
        const val FIELD_DATE_RANGE_FINISH: String = "to"
        const val FIELD_DOMAIN: String = "domains"
        const val FIELD_QUERY: String = "q"
        const val FIELD_SORT: String = "sortBy"
        const val FIELD_SOURCE: String = "sources"

        //    business entertainment general health science sports technology
    }
}