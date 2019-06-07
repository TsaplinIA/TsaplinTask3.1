package logics

class Nucleus {

    enum class ForIns {
        EMPTY, BOMB, N_1, N_2, N_3, N_4, N_5, N_6
    }

    enum class ForStatus {
        CLOSE, OPEN, FLAG
    }

    var ins = ForIns.EMPTY// empty/bomb/1/2/3/4/5/6
    var status = ForStatus.CLOSE// close/open/flag
    var immunity = false // true/false

    fun fillingMyselfBomb(freeBombCount: Int, row: Int, column: Int, w: Int, h: Int): Boolean {
        val free = w * (h - row) - column - 1
        if (!immunity && freeBombCount > 0)
            if (free < 1 && freeBombCount > 0) {
                ins = ForIns.BOMB
                return true
            } else if ((0..100).random() <= 100 * freeBombCount / free) {
                ins = ForIns.BOMB
                return true
            }
        return false
    }
}