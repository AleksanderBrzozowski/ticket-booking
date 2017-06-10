package com.maly.presentation.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * @author Aleksander Brzozowski
 */
class ErrorInfo(val message: String, val code: String){

    enum class Type(val statusCode: HttpStatus){
        BUSINESS(HttpStatus.BAD_REQUEST), SYSTEM(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        fun of(message: String, code: String, type: Type): ResponseEntity<ErrorInfo> {
            return ErrorInfo(message = message, code = code)
                    .let { ResponseEntity<ErrorInfo>(it, type.statusCode) }
        }
    }
}