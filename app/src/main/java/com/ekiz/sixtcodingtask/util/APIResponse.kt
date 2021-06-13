package com.ekiz.sixtcodingtask.util

sealed class APIResponse <out L, out R> {

    data class Failure<out L>(val a: L) : APIResponse<L, Nothing>()

    data class Success<out R>(val b: R) : APIResponse<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Failure<L>

    fun <L> fail(a: L) = Failure(a)
    fun <R> right(b: R) = Success(b)

    fun decide(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }
}