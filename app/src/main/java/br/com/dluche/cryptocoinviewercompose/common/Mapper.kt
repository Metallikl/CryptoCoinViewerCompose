package br.com.dluche.cryptocoinviewercompose.common

interface Mapper<in F,out T> {
    fun mapTo(from: F): T
}