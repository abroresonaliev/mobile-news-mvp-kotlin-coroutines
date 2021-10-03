package uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.converter

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

internal object DateSerializer {
    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    object NotNullable : KSerializer<Date> {
        override val descriptor = PrimitiveSerialDescriptor(
            serialName = "DateSerializer",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): Date =
            checkNotNull(dateFormat.parse(decoder.decodeString()))

        override fun serialize(encoder: Encoder, value: Date) {
            encoder.encodeString(dateFormat.format(value))
        }
    }

    object Nullable : KSerializer<Date?> {
        override val descriptor = PrimitiveSerialDescriptor(
            serialName = "DateSerializer",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): Date? =
            dateFormat.parse(decoder.decodeString())

        override fun serialize(encoder: Encoder, value: Date?) {
            value?.let { encoder.encodeString(dateFormat.format(value)) }
        }
    }
}