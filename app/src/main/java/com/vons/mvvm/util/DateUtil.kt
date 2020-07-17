package com.vons.mvvm.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
object DateUtil {
    private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    private const val DATE_PATTERN = "yyyy-MM-dd"
    private val dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)
    private val dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    fun date2LocalDateTimeString(date: Date): String {
        return dateTimeFormatter.format(
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        )
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    fun timeStamp2LocalDateTimeString(timeStamp: Long): String {
        return dateTimeFormatter.format(
            LocalDateTime.ofInstant(
                Date(timeStamp).toInstant(),
                ZoneId.systemDefault()
            )
        )
    }

    /**
     * yyyy-MM-dd
     */
    fun date2LocalDateString(date: Date): String {
        return dateFormatter.format(
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        )
    }

    /**
     * yyyy-MM-dd
     */
    fun timeStamp2LocalDateString(timeStamp: Long): String {
        return dateFormatter.format(
            LocalDateTime.ofInstant(
                Date(timeStamp).toInstant(),
                ZoneId.systemDefault()
            )
        )
    }

    /**
     * Date转为LocalDateTime
     */
    fun date2LocalDateTime(date: Date): LocalDateTime {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }

    /**
     * Date转为LocalDate
     */
    fun date2LocalDate(date: Date): LocalDate {
        return LocalDate.ofEpochDay(date.time)
    }

    /**
     * String转为LocalDateTime
     */
    fun string2LocalDateTime(date: String): LocalDateTime {
        return LocalDateTime.parse(date, dateTimeFormatter)
    }

    /**
     * String转为LocalDate
     */
    fun string2LocalDate(date: String): LocalDate {
        return LocalDate.parse(date, dateTimeFormatter)
    }
}