package com.ekiz.sixtcodingtask.util

import java.lang.Exception

sealed class ErrorException : Exception() {

    object NoConnectionError : ErrorException()
    class APIError(override var message: String? = null) : ErrorException()

}