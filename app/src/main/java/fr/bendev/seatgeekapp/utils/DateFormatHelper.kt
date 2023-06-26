package fr.bendev.seatgeekapp.utils

import android.content.Context
import androidx.annotation.StringRes
import fr.bendev.seatgeekapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatHelper {

    private fun formatDate(context: Context, date: Date, @StringRes withFormat: Int): String =
        SimpleDateFormat(context.getString(withFormat), Locale.getDefault()).format(date)

    fun formatFullDate(context: Context, date: Date): String =
        formatDate(context = context, date = date, withFormat = R.string.date_format_day_month_year)

}