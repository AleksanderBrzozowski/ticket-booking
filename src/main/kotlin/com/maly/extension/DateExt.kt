package com.maly.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

/**
 * @author Aleksander Brzozowski
 */


private val DATE_TIME_FORMATTER = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
        .withLocale(Locale("PL"))

fun LocalDateTime.toPolishDate() = DATE_TIME_FORMATTER.format(this)