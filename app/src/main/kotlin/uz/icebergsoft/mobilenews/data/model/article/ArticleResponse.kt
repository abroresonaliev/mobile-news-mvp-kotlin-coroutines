package uz.icebergsoft.mobilenews.data.model.article


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.icebergsoft.mobilenews.data.datasource.network.retrofit.converter.DateSerializer
import uz.icebergsoft.mobilenews.data.model.source.SourceResponse
import java.util.*

@Serializable
internal data class ArticleResponse(

    @SerialName("author")
    val author: String? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("description")
    val description: String? = null,

    @Serializable(with = DateSerializer::class)
    @SerialName("publishedAt")
    val publishedAt: Date? = null,

    @SerialName("source")
    val source: SourceResponse,

    @SerialName("title")
    val title: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("urlToImage")
    val imageUrl: String? = null
)