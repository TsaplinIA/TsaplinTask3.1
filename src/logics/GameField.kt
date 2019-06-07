package logics

class GameField(val height: Int, val width: Int) {
    private val matrixBody = MutableList(height) { MutableList(width) { Nucleus() } }

    fun get(row: Int, column: Int): Nucleus = matrixBody[row][column]

    fun set(row: Int, column: Int, value: Nucleus) {
        matrixBody[row][column] = value
    }

    var isFirstClick = true
    var bombCount = 0
    var needOpen = 5
    var bedEnd = false
    var goodEnd = false

    private fun fillingBomb() {
        for (i in 0 until height)
            for (j in 0 until width)
                if (matrixBody[i][j].fillingMyselfBomb(bombCount, i, j, width, height)) {
                    bombCount--
                }
    }

    private fun getEnv(row: Int, column: Int): List<Pair<Int, Int>> = listOf(
        0 to -1,
        0 to +1,
        -1 to if (row % 2 > 0) 0 else -1,
        -1 to if (row % 2 > 0) +1 else 0,
        +1 to if (row % 2 > 0) 0 else -1,
        +1 to if (row % 2 > 0) +1 else 0
    )
        .map { row + it.first to column + it.second }
        .filter { it.first in 0 until height && it.second in 0 until width }



    fun fillingNum() {
        for (i in 0 until height)
            for (j in 0 until width) {
                var allPair = getEnv(i, j)
                var count = 0
                for (el in allPair)
                    if (matrixBody[el.first][el.second].ins == Nucleus.ForIns.BOMB) count++
                if (matrixBody[i][j].ins != Nucleus.ForIns.BOMB) {
                    when (count) {
                        0 -> matrixBody[i][j].ins = Nucleus.ForIns.EMPTY
                        1 -> matrixBody[i][j].ins = Nucleus.ForIns.N_1
                        2 -> matrixBody[i][j].ins = Nucleus.ForIns.N_2
                        3 -> matrixBody[i][j].ins = Nucleus.ForIns.N_3
                        4 -> matrixBody[i][j].ins = Nucleus.ForIns.N_4
                        5 -> matrixBody[i][j].ins = Nucleus.ForIns.N_5
                        6 -> matrixBody[i][j].ins = Nucleus.ForIns.N_6
                    }
                }
            }
    }

    fun allFilling(immRow: Int, immColumn: Int, bombs: Int) {
        matrixBody[immRow][immColumn].immunity = true
        bombCount = bombs
        needOpen = height * width - bombCount
        fillingBomb()
        fillingNum()
        hardOpen(immRow, immColumn)
    }

    fun hardOpen(row: Int, column: Int) {
        val cell = matrixBody[row][column]
        if (cell.status != Nucleus.ForStatus.FLAG) easyOpen(row, column)
        if (cell.ins == Nucleus.ForIns.BOMB && cell.status != Nucleus.ForStatus.FLAG) {
            cell.status = Nucleus.ForStatus.OPEN
            bedEnd = true
        }
    }

    private fun easyOpen(row: Int, column: Int) {
        val cell = matrixBody[row][column]
        if (cell.status != Nucleus.ForStatus.OPEN) {
            if(cell.ins != Nucleus.ForIns.BOMB) {
                cell.status = Nucleus.ForStatus.OPEN
                needOpen--
                if (needOpen == 0) {
                    println("WIN!!!")
                    goodEnd = true
                }
            }
            if(cell.ins == Nucleus.ForIns.EMPTY) getEnv(row, column).forEach { easyOpen(it.first, it.second) }
        }
    }

    fun addFlag(row: Int, column: Int) {
        if (matrixBody[row][column].status == Nucleus.ForStatus.FLAG) {
            matrixBody[row][column].status = Nucleus.ForStatus.CLOSE
        }
        else if (matrixBody[row][column].status == Nucleus.ForStatus.CLOSE) {
            matrixBody[row][column].status = Nucleus.ForStatus.FLAG
        }
    }
}