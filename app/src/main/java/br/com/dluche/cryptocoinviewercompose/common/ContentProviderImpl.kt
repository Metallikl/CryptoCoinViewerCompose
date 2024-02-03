package br.com.dluche.cryptocoinviewercompose.common

import android.content.Context

class ContentProviderImpl(
    private val context: Context
) : ContentProvider {
    override fun getString(res: Int): String {
        return context.getString(res)
    }
}