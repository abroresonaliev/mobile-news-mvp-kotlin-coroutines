package uz.icerbersoft.mobilenews.data.datasource.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

internal class RoomTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?): String? =
        date?.time?.let { dateFormat.format(it) }

    @TypeConverter
    fun toDate(string: String?): Date? =
        string?.let { dateFormat.parse(it) }

    @Suppress("SpellCheckingInspection")
    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("uz", "UZ"))
}