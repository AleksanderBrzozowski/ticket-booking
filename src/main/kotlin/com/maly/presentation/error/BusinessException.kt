package com.maly.presentation.error

/**
 * @author Aleksander Brzozowski
 */
class BusinessException(val messageCode: String, val params: Array<String> = arrayOf(), val systemMessage: String = ""): RuntimeException(messageCode)