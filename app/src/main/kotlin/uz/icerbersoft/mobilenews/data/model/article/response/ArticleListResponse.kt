package uz.icerbersoft.mobilenews.data.model.article.response


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