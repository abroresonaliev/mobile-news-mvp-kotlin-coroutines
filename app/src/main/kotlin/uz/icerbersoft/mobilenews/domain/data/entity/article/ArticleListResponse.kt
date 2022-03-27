package uz.icerbersoft.mobilenews.domain.data.entity.article


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleListResponse(

    @SerialName("articles")
    val articles: List<ArticleResponse>,

    @SerialName("status")
    val status: String,

    @SerialName("totalResults")
    val totalResults: Int
)