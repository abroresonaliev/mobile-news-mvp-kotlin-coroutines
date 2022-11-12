package uz.icebergsoft.mobilenews.data.utils.date

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedDate(format: String = "MMMM dd, yyyy"): String =
    SimpleDateFormat(format, Locale.US).format(this)
