package dev.px.Util.Math

class Vec3d(val x: Double, val y: Double, val z: Double) : Vector() {

    override val magnitude: Double
        get() = Math.sqrt((x * x) + (y * y) + (z * z))

    override val absolute: Double
        get() = Math.abs(x + y + z)

    override fun add(vector: Vector): Vector {
        if (vector is Vec3d) {
            return Vec3d(x + vector.x, y + vector.y, z + vector.z)
        } else {
            throw IllegalArgumentException("Cannot combine Vec3d with other vector types")
        }
    }

    override fun subtract(vector: Vector): Vector {
        if (vector is Vec3d) {
            return Vec3d(x - vector.x, y - vector.y, z - vector.z)
        } else {
            throw IllegalArgumentException("Cannot combine Vec3d with other vector types")
        }
    }

    override fun multiply(scalar: Double): Vector {
        return Vec3d(x * scalar, y * scalar, z * scalar)
    }

    override fun divide(scalar: Double): Vector {
        require(scalar != 0.0) { "Cannot divide by zero" }
        return Vec3d(x / scalar, y / scalar, z / scalar)
    }

    override fun dot(vector: Vector): Double {
        if (vector is Vec3d) {
            return (x * vector.x) + (y * vector.y) + (z * vector.z)
        } else {
            throw IllegalArgumentException("Cannot combine Vec3d with other vector types")
        }
    }

    override fun scale(scalar: Double): Vector {
        return Vec3d(x * scalar, y * scalar, z * scalar)
    }

    override fun projection(vector: Vector): Vector {
        if (vector is Vec3d) {
            val dotProduct = dot(vector)
            val magnitudeSquared = vector.magnitude * vector.magnitude
            val scale = dotProduct / magnitudeSquared
            return Vec3d(vector.x * scale, vector.y * scale, vector.z * scale)
        } else {
            throw IllegalArgumentException("Cannot combine Vec3d with other vector types")
        }
    }

    override fun sum(): Double {
        return x + y + z
    }

}