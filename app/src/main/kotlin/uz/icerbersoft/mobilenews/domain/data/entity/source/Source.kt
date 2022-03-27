package uz.icerbersoft.mobilenews.domain.data.entity.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(

    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String
)