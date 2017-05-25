package com.maly.presentation.error

import com.maly.extension.getDefaultMessage
import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author Aleksander Brzozowski
 */

@ControllerAdvice
class ExceptionHandler(private val messageSource: MessageSource) {

    @ExceptionHandler
    fun handleBusinessException(exc: BusinessException): ResponseEntity<ErrorInfo>{
        val code = exc.messageCode
        return messageSource.getDefaultMessage(code)
                .let { ErrorInfo.of(message = it, code = code, type = ErrorInfo.Type.BUSINESS) }
    }


}