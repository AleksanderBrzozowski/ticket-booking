package com.maly.presentation.error

import com.maly.extension.getDefaultMessage
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author Aleksander Brzozowski
 */

@ControllerAdvice
class ExceptionHandler(private val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

    @ExceptionHandler
    fun handleBusinessException(exc: BusinessException): ResponseEntity<ErrorInfo>{
        val code = exc.messageCode
        logger.error("Business exception: [$code], system message: [${exc.systemMessage}]", exc)
        return messageSource.getDefaultMessage(code)
                .let { ErrorInfo.of(message = it, code = code, type = ErrorInfo.Type.BUSINESS) }
    }

    @ExceptionHandler
    fun handleException(exc: Exception) : ResponseEntity<ErrorInfo>{
        val code = "unknownError"
        logger.error("System exception: [${exc.message}", exc)
        return messageSource.getDefaultMessage(code)
                .let { ErrorInfo.of(message = it, code = code, type = ErrorInfo.Type.SYSTEM) }
    }

}