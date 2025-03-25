package dev.px.Util.ADT

import kotlin.Pair

class Pair<K, V> {

    var key: K
    var value: V

    constructor(key: K, value: V) {
        this.key = key
        this.value = value
    }

    fun <K, V> of(key: K, value: V): Pair<K, V> {
        return Pair(key, value)
    }

    fun <K, V> Pair<K, V>.use(func: (K, V) -> Unit) {
        func(first, second)
    }

}