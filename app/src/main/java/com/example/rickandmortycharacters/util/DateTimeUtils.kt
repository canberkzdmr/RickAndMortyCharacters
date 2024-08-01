package com.example.rickandmortycharacters.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateTimeUtils {
    fun formatDate(inputDateStr: String?): String {
        return inputDateStr?.let {
            try {
                val inputFormat =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
                        timeZone = TimeZone.getTimeZone("UTC")
                    }
                val date = inputFormat.parse(it)

                SimpleDateFormat("HH:mm:ss dd MMMM yyyy", Locale.getDefault()).format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        } ?: ""
    }
}
