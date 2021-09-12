package org.github.henryquan.pazusoba_android.core

class Pazusoba(var minErase: Int = 3, var maxStep: Int = 30, var beamSize: Int = 2500) {

    fun solve(board: String, callback: (String) -> Unit) {
        Thread {
            val start = System.currentTimeMillis()
            val data = solve(board, minErase, maxStep, beamSize)
            val route = Route(board, data)
            if (route.valid) {
                val end = System.currentTimeMillis() - start
                var info =
                    route.routeString + "\nIt took $end ms\n${route.steps} step(s) in total\nBeam - $beamSize, Step - $maxStep\n$board\n${route.combo} combo"
                callback(info)
            } else {
                callback("")
            }
        }.start()
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