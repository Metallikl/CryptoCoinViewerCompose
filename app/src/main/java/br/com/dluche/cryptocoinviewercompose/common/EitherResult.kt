package br.com.dluche.cryptocoinviewercompose.common

sealed class EitherResult<out S> {
    data class Success<S>(val data:S): EitherResult<S>()
    data class Error(val error:Throwable): EitherResult<Nothing>()
}