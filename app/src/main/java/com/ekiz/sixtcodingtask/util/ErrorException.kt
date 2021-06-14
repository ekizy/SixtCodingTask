package com.ekiz.sixtcodingtask.util

import java.io.IOException

sealed class ErrorException : IOException() {

    object EmptyResponse : ErrorException()
    object NoConnectionError : ErrorException()
    class APIError(override var message: String?) : ErrorException()

}