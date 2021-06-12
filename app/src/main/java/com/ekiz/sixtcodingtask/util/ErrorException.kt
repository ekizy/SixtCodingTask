package com.ekiz.sixtcodingtask.util

import java.io.IOException

sealed class ErrorException : IOException() {

    class ApiError(var code: Int, override var message: String) : ErrorException()
    object EmptyResponse : ErrorException()
    object NoConnectionError : ErrorException()
    object UnknownError : ErrorException()

}