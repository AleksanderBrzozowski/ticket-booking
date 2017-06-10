package com.maly.extension

import org.springframework.context.MessageSource
import java.util.*

/**
 * @author Aleksander Brzozowski
 */
private val defaultLocale = Locale.getDefault()

fun MessageSource.getDefaultMessage(code: String, params: Array<String> = arrayOf()) = getMessage(code, params, defaultLocale)!!