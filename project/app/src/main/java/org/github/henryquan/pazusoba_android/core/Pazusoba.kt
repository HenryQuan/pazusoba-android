package org.github.henryquan.pazusoba_android.core

object Pazusoba {
    // Load the shared library
    init {
        System.loadLibrary("pazusoba_android")
    }

    external suspend fun solve(
        board: String,
        minErase: Int = 3,
        maxStep: Int = 30,
        beamSize: Int = 2500
    ): String
}