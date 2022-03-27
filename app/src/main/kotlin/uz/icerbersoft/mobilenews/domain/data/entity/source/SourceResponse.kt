package uz.icerbersoft.mobilenews.domain.data.entity.source


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SourceResponse(

    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String
)