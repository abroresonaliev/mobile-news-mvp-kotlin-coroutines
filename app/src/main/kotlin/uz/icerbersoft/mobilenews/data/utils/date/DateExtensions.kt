package uz.icerbersoft.mobilenews.data.utils.date

import java.text.SimpleDateFormat
import java.util.*

fun Date.mapToString(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): String =
    SimpleDateFormat(format, Locale.US).format(this)

fun Long.mapToDate(): Date =
    Date(if (this > SECOND_9_999_999_999) this else this * 1000)

internal fun Long.toServerDate(): Long =
    if (this > SECOND_9_999_999_999) this / 1000 else this

private const val SECOND_9_999_999_999 = 9999999999L
