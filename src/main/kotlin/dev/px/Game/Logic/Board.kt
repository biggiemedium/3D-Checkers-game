package dev.px.Game.Logic

import dev.px.Util.Math.Vec3d

/**
 * Represents a 3D checkerboard game board.
 *
 * The `Board` class defines a 4x4x4 cubic board layout in 3D space, which is equivalent to a traditional 8x8
 * 2D checkerboard in terms of the number of cells (64).
 *
 * @property bounds The half-size limit of the board along each axis. Given a 4x4x4 layout, `bounds` is set to 4,
 * resulting in coordinates ranging from (0 - max size) for each dimension (x, y, z).
 * @property checkers A map that associates `Checker` objects with `Player` objects. Uses a `LinkedHashMap`
 * to maintain insertion order, which may be useful for iterating over checker placements in the order they were added.
 */
class Board {

    var bounds: Int = 8 // 4 x 4 x 4 = 64 If we were to make in 2d 8x8 = 64 (Normal board size is 8x8)
    var checkers: MutableMap<Vec3d, Checker?> = mutableMapOf() // <Vec3d> For Checker Position <Checker> For player checker

    init {
        makeBoundsEven()
        createBoard()
    }

    constructor() {

    }

    /**
     * Creates the 3D checkerboard by iterating through each coordinate in the bounds.
     *
     * The method uses a nested loop to iterate over all possible `(x, y, z)` positions in 3D space within the specified
     * bounds (-4 to 4 for each axis). The actual creation logic can be implemented inside the nested loops.
     */
    fun createBoard() {
        makeBoundsEven() // just in case we forget later on
        for (x in 0..bounds) {
            for(y in 0..bounds) {
                for(z in 0..bounds) {
                    var position: Vec3d = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
                    var isDark: Boolean = (position.sum() % 2) == 0.0

                    if(isDark) {
                        val color = if ((x + y + z) % 4 == 0) CheckerColor.WHITE else CheckerColor.BLACK
                        val checker = Checker(color, CheckerState.Normal) // we always default to normal in starting checkers game
                        checkers[position] = checker
                    } else {
                        checkers[position] = null
                    }

                }
            }
        }
    }

    fun restartGame() {
        checkers.clear()
        createBoard()
    }

    /**
     * Checks if a given position is outside the bounds of the board.
     *
     * @param position The position in 3D space (x, y, z) to check.
     * @param bounds The allowed range (as a positive double) from `-bounds` to `+bounds`.
     * @return `true` if the position is outside the board bounds, `false` otherwise.
     */
    fun isOutOfBounds(position: Vec3d, bounds: Double): Boolean {
        return position.x !in 0.0..bounds ||
                position.y !in 0.0..bounds ||
                position.z !in 0.0..bounds
    }

    fun makeBoundsEven() { // We want bounds to be a perfect square
        if((bounds % 2) != 0) {
            bounds += 1
        }
    }

    fun findCentre(): Vec3d {
        return Vec3d(bounds / 2.0, bounds / 2.0, bounds / 2.0)
    }

    // For fun to spice up the game
    fun transpose() {
        val newCheckers = mutableMapOf<Vec3d, Checker?>()
        for ((position, checker) in checkers) {
            val newPosition = Vec3d(position.y, position.x, position.z)
            newCheckers[newPosition] = checker
        }
        checkers.clear()
        checkers.putAll(newCheckers)
    }

}