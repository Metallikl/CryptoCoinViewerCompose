package br.com.dluche.cryptocoinviewercompose.extentions

fun Boolean.toInt(): Int {
    return when (this) {
        true -> 1
        false -> 0
    }
}

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Boolean?.orDefault(default: Boolean = false): Boolean {
    return this ?: default
}

fun Int?.orDefault(default: Int = 0): Int {
    return this ?: default
}