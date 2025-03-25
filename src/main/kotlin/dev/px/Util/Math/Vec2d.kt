package dev.px.Util.Math

class Vec2d(val x: Double, val y: Double) : Vector() {

    override val magnitude: Double
        get() = Math.sqrt((x * x) + (y * y))

    override val absolute: Double
        get() = Math.abs(x + y)

    override fun add(vector: Vector): Vector {
        if (vector is Vec2d) {
            return Vec2d(x + vector.x, y + vector.y)
        } else {
            throw IllegalArgumentException("Cannot combine Vec2d with other vector types")
        }
    }

    override fun subtract(vector: Vector): Vector {
        if (vector is Vec2d) {
            return Vec2d(x - vector.x, y - vector.y)
        } else {
            throw IllegalArgumentException("Cannot combine Vec2d with other vector types")
        }
    }

    override fun multiply(scalar: Double): Vector {
        return Vec2d(x * scalar, y * scalar)
    }

    override fun divide(scalar: Double): Vector {
        require(scalar != 0.0) { "Cannot divide by zero" }
        return Vec2d(x / scalar, y / scalar)
    }

    override fun dot(vector: Vector): Double {
        if (vector is Vec2d) {
            return (x * vector.x) + (y * vector.y)
        } else {
            throw IllegalArgumentException("Cannot combine Vec2d with other vector types")
        }
    }

    override fun scale(scalar: Double): Vector {
        return Vec2d(x * scalar, y * scalar)
    }

    override fun projection(vector: Vector): Vector {
        if (vector is Vec2d) {
            val dotProduct = dot(vector)
            val magnitudeSquared = vector.magnitude * vector.magnitude
            val scale = dotProduct / magnitudeSquared
            return Vec2d(vector.x * scale, vector.y * scale)
        } else {
            throw IllegalArgumentException("Cannot combine Vec2d with other vector types")
        }
    }

    override fun sum(): Double {
        return x + y
    }
}