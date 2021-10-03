package uz.icerbersoft.mobilenews.data.model.article.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SourceResponse(

    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String
)