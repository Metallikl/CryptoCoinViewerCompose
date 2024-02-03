package br.com.dluche.cryptocoinviewercompose.common

import androidx.annotation.StringRes

interface ContentProvider {
    fun getString(@StringRes res:Int): String
}