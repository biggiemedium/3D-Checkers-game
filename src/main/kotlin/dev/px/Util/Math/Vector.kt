package dev.px.Util.Math

abstract class Vector {

    abstract val magnitude: Double
    abstract val absolute: Double

    abstract fun add(vector: Vector): Vector
    abstract fun subtract(vector: Vector): Vector
    abstract fun multiply(scalar: Double): Vector
    abstract fun divide(scalar: Double): Vector
    abstract fun sum(): Double

    abstract fun dot(vector: Vector): Double
    abstract fun scale(scalar: Double): Vector
    abstract fun projection(vector: Vector): Vector
}