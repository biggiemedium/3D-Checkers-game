package dev.px.Util.ADT

class Tuple<K, V, O> {

    var key: K
    var value: V
    var other: O

    constructor(key: K, value: V, other: O) {
        this.key = key
        this.value = value
        this.other = other;
    }

    fun <K, V, O> of(key: K, value: V, other: O): Tuple<K, V, O> {
        return Tuple(key, value, other)
    }
}