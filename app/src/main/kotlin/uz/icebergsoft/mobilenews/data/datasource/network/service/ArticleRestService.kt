package uz.icebergsoft.mobilenews.data.datasource.network.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import uz.icebergsoft.mobilenews.data.model.article.ArticleListResponse

internal interface ArticleRestService {

    @GET(API_TOP_HEADINGS)
    fun getArticles(
        @Query(FIELD_COUNTRY) country: String,
        @Query(FIELD_CATEGORY) category: String,
        @Query(FIELD_SORT) sortBy: String
    ): Flow<ArticleListResponse>

    private companion object {
        const val API_TOP_HEADINGS: String = "top-headlines"

        const val FIELD_COUNTRY: String = "country"
        const val FIELD_CATEGORY: String = "category"
        const val FIELD_SORT: String = "sortBy"
    }
}