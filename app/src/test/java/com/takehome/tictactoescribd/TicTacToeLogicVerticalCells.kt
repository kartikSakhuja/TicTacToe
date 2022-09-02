package com.takehome.tictactoescribd

import com.takehome.tictactoescribd.data.Cell
import com.takehome.tictactoescribd.domain.TicTacToeLogic
import com.takehome.tictactoescribd.data.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import kotlin.Throws

class TicTacToeLogicVerticalCells {
    private var ticTacToeLogic: TicTacToeLogic? = null
    private var user1: User? = null
    private var user2: User? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        ticTacToeLogic = TicTacToeLogic("Kartik", "CPU")
        user1 = ticTacToeLogic!!.user1
        user2 = ticTacToeLogic!!.user2
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameVerticalCellsAtColumn1() {
        val cell = Cell(user1)
        ticTacToeLogic!!.cells!![0][0] = cell
        ticTacToeLogic!!.cells!![1][0] = cell
        ticTacToeLogic!!.cells!![2][0] = cell
        val hasThreeSameVerticalCells = ticTacToeLogic!!.hasThreeSameVerticalCells()
        Assert.assertTrue(hasThreeSameVerticalCells)
    }


    @Test
    @Throws(Exception::class)
    fun returnFalseIfDoesNotHaveThreeSameVerticalCells() {
        val cell = Cell(user1)
        val anotherCell = Cell(user2)
        ticTacToeLogic!!.cells!![0][0] = cell
        ticTacToeLogic!!.cells!![1][0] = cell
        ticTacToeLogic!!.cells!![2][0] = anotherCell
        val hasThreeSameVerticalCells = ticTacToeLogic!!.hasThreeSameVerticalCells()
        Assert.assertFalse(hasThreeSameVerticalCells)
    }
}