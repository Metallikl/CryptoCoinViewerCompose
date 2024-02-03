package br.com.dluche.cryptocoinviewercompose.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppCoroutinesDispatchers {
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun main(): CoroutineDispatcher = Dispatchers.Main
}
