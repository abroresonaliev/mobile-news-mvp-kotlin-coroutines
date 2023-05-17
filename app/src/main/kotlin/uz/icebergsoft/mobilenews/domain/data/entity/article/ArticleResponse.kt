package uz.icebergsoft.mobilenews.domain.data.entity.article


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.icebergsoft.mobilenews.data.datasource.network.retrofit.converter.DateSerializer
import uz.icebergsoft.mobilenews.domain.data.entity.source.SourceResponse
import java.util.*

@Serializable
internal data class ArticleResponse(

    @SerialName("author")
    val author: String?,

    @SerialName("content")
    val content: String?,

    @SerialName("description")
    val description: String?,

    @Serializable(with = DateSerializer::class)
    @SerialName("publishedAt")
    val publishedAt: Date?,

    @SerialName("source")
    val source: SourceResponse,

    @SerialName("title")
    val title: String,

    @SerialName("url")
    val url: String,

    @SerialName("urlToImage")
    val imageUrl: String?
)