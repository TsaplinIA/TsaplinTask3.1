package control

import logics.GameField
import graphics.HexagonPattern
import graphics.sliders.Bombs
import graphics.sliders.MySlider
import logics.Nucleus
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import javax.swing.JOptionPane

class GameController {
    private val graphPole = JFrame()
    private val hexPole = HexagonPattern()
    val logicPole: GameField

    private val sideSlider = MySlider()
    private val bombSlider = Bombs()

    private val side: Int

    init {
        sideSlider.isModal = true
        sideSlider.isVisible = true
        side = sideSlider.slider.value

        logicPole = GameField(side, side)

        bombSlider.max = (side * side) / 2 - 1
        bombSlider.reload()
        bombSlider.isModal = true
        bombSlider.isVisible = true

        logicPole.bombCount = bombSlider.slider.value
        hexPole.ROWS = side
        hexPole.COLUMNS = side
        hexPole.initGUI()
        hexButtonActionAdded()

        graphPole.title = "Сапер"
        graphPole.defaultCloseOperation= JFrame.EXIT_ON_CLOSE
        graphPole.location = Point(0, 0)
        graphPole.add(hexPole)
        graphPole.setSize(500, 500)
        graphPole.isResizable = false
        graphPole.isVisible = true
    }

    private fun hexButtonActionAdded() {
        for (i in 0 until logicPole.height)
            for (j in 0 until logicPole.width) {
                hexPole.hexButton[i][j].addMouseListener(object : MouseAdapter() {
                    override fun mousePressed(e: MouseEvent) {
                        if (MouseEvent.BUTTON3 === e.getButton()) {
                            logicPole.addFlag(i, j)
                            checkChange()
                        } else if (e.getButton() === MouseEvent.BUTTON1) {
                            if (logicPole.isFirstClick) {
                                logicPole.isFirstClick = false
                                logicPole.allFilling(i, j, logicPole.bombCount)
                                checkChange()
                            }else  {
                                logicPole.hardOpen(i, j)
                                checkChange()
                            }
                        }
                    }
                })
            }
    }

    fun checkChange() {
        for (i in 0 until logicPole.height)
            for (j in 0 until logicPole.width) {
                val cellNow = logicPole.get(i, j)
                when {
                    cellNow.status == Nucleus.ForStatus.CLOSE -> hexPole.hexButton[i][j].text = " "
                    cellNow.status == Nucleus.ForStatus.FLAG -> hexPole.hexButton[i][j].text = "F"
                    cellNow.ins == Nucleus.ForIns.BOMB -> hexPole.hexButton[i][j].text = "B"
                    cellNow.ins == Nucleus.ForIns.EMPTY -> hexPole.hexButton[i][j].text = "0"
                    cellNow.ins == Nucleus.ForIns.N_1 -> hexPole.hexButton[i][j].text = "1"
                    cellNow.ins == Nucleus.ForIns.N_2 -> hexPole.hexButton[i][j].text = "2"
                    cellNow.ins == Nucleus.ForIns.N_3 -> hexPole.hexButton[i][j].text = "3"
                    cellNow.ins == Nucleus.ForIns.N_4 -> hexPole.hexButton[i][j].text = "4"
                    cellNow.ins == Nucleus.ForIns.N_5 -> hexPole.hexButton[i][j].text = "5"
                    cellNow.ins == Nucleus.ForIns.N_6 -> hexPole.hexButton[i][j].text = "6"
                }
            }

        if (logicPole.bedEnd) {
            openAll()
            JOptionPane.showMessageDialog(JOptionPane(),
                "К сожалению, Вы наткнулись на бомбу",
                "Поражение=(",
                1)
            System.exit(0)
        }

        if (logicPole.goodEnd) {
            openAll()
            JOptionPane.showMessageDialog(JOptionPane(),
                        "Вы нашли все клетки, в которых небыло бомбы",
                        "Победа!!!",
                        1)
            System.exit(0)
        }
    }

    private fun openAll() {
        for (i in 0 until logicPole.height)
            for (j in 0 until logicPole.width) {
                val cellNow = logicPole.get(i, j)
                when {
                    cellNow.ins == Nucleus.ForIns.BOMB -> hexPole.hexButton[i][j].text = "B"
                    cellNow.ins == Nucleus.ForIns.EMPTY -> hexPole.hexButton[i][j].text = "0"
                    cellNow.ins == Nucleus.ForIns.N_1 -> hexPole.hexButton[i][j].text = "1"
                    cellNow.ins == Nucleus.ForIns.N_2 -> hexPole.hexButton[i][j].text = "2"
                    cellNow.ins == Nucleus.ForIns.N_3 -> hexPole.hexButton[i][j].text = "3"
                    cellNow.ins == Nucleus.ForIns.N_4 -> hexPole.hexButton[i][j].text = "4"
                    cellNow.ins == Nucleus.ForIns.N_5 -> hexPole.hexButton[i][j].text = "5"
                    cellNow.ins == Nucleus.ForIns.N_6 -> hexPole.hexButton[i][j].text = "6"
                }
            }
    }
}

fun main(args: Array<String>) {
    GameController()
}