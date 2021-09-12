package org.github.henryquan.pazusoba_android.core

class Route(board: String, data: IntArray) {
    private val rawData: List<Int> = data.asList()

    // the last index is combo
    private val stepList: List<Int> = rawData.take(rawData.size - 1)
    private val column: Int = getColumn(board.length)

    val combo: Int = rawData.last()
    val routeString: String
    val valid = data.isNotEmpty()
    val steps = stepList.size

    init {
        var temp = ""
        for (step in stepList) {
            val x = step / column + 1
            val y = step % column + 1
            temp += "($x, $y) -> "
        }
        temp += "END"
        routeString = temp
    }

    private fun getColumn(length: Int): Int {
        return when (length) {
            20 -> 5
            30 -> 6
            42 -> 7
            else -> error("Unknown board size ${length}")
        }
    }
}
