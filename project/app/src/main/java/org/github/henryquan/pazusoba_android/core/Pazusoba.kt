package org.github.henryquan.pazusoba_android.core

import android.util.Log

class Pazusoba(val minErase: Int = 3, val maxStep: Int = 30, val beamSize: Int = 2500) {

    fun solve(board: String): String {
        val start = System.currentTimeMillis()
        val stepList = solve(board, minErase, maxStep, beamSize)
        val size = stepList.count()
        val column = getColumn(board.length)

        var route = ""
        for (step in stepList) {
            val x = step / column
            val y = step % column
            route += "($x, $y) -> "
        }
        route += "END"

        val end = System.currentTimeMillis() - start
        route += "\nIt took $end ms\n$size step(s) in total\nBeam - $beamSize, Step - $maxStep"
        return route
    }

    private fun getColumn(length: Int): Int {
        return when (length) {
            20 -> 5
            30 -> 6
            42 -> 7
            else -> error("Unknown board size ${length}")
        }
    }

    /**
     * Native Methods
     */

    private external fun solve(
        board: String,
        minErase: Int,
        maxStep: Int,
        beamSize: Int,
    ): IntArray

    // Load the shared library once only
    companion object {
        init {
            System.loadLibrary("pazusoba_android")
        }
    }
}