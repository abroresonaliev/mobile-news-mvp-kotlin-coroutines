package uz.icerbersoft.mobilenews.data.model.article.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.converter.DateSerializer
import java.util.*

@Serializable
internal data class ArticleResponse(

    @SerialName("author")
    val author: String?,

    @SerialName("content")
    val content: String?,

    @SerialName("description")
    val description: String?,

    @Serializable(with = DateSerializer.Nullable::class)
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