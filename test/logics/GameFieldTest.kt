package logics

import org.junit.Assert.*
import org.junit.Test

class GameFieldTest {
    val pole1 = GameField(3,3)
    @Test
    fun fillingNum() {
        val cell = Nucleus()
        cell.ins = Nucleus.ForIns.BOMB
        pole1.set(1, 1, cell)
        pole1.fillingNum()
        assertEquals(Nucleus.ForIns.N_1, pole1.get(0, 1).ins)
        assertEquals(Nucleus.ForIns.N_1, pole1.get(0, 2).ins)
        assertEquals(Nucleus.ForIns.N_1, pole1.get(1, 0).ins)
        assertEquals(Nucleus.ForIns.N_1, pole1.get(1, 2).ins)
        assertEquals(Nucleus.ForIns.N_1, pole1.get(2, 1).ins)
        assertEquals(Nucleus.ForIns.N_1, pole1.get(2, 2).ins)
    }

    @Test
    fun hardOpen() {
        val cell = Nucleus()
        cell.ins = Nucleus.ForIns.BOMB
        pole1.set(1, 1, cell)
        pole1.hardOpen(1, 1)
        assertEquals(Nucleus.ForIns.BOMB, pole1.get(1, 1).ins)
        assertTrue(pole1.bedEnd)
    }

}