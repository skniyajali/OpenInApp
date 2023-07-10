package com.niyaj.openinappold.features.common.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.core.content.ContextCompat.getSystemService
import java.util.Calendar
import java.util.Date
import java.util.Locale


object Extensions {

    fun getGreetingHour(): String {
        val calendar = Calendar.getInstance()
        return when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..15 -> "Good afternoon"
            in 16..20 -> "Good evening"
            in 21..23 -> "Good night"
            else -> "Hello"
        }
    }

    fun formattedDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val parsedDate: Date = inputFormat.parse(date)

        return outputFormat.format(parsedDate)
    }

    fun copyToClipboard(context: Context, text: String) {
        val clipboardManager = getSystemService(context, ClipboardManager::class.java)
        val clipData = ClipData.newPlainText("text", text)
        clipboardManager?.setPrimaryClip(clipData)
    }


    fun getFormattedMonth(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM", Locale.getDefault())

        val parsedDate: Date = inputFormat.parse(date)

        return outputFormat.format(parsedDate)
    }

    private fun getFormattedDateAndMonth(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())

        val parsedDate: Date = inputFormat.parse(date)

        return outputFormat.format(parsedDate)
    }

    fun Map<String, Int>.getFirstDate(): String {
        return try {
            getFormattedDateAndMonth(this.entries.first().key)
        }catch (e: Exception) {
            ""
        }
    }

    fun Map<String, Int>.getLastDate(): String {
        return try {
            getFormattedDateAndMonth(this.entries.last().key)
        }catch (e: Exception) {
            ""
        }
    }
}