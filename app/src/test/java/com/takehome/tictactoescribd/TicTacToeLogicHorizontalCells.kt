package com.takehome.tictactoescribd

import com.takehome.tictactoescribd.data.Cell
import com.takehome.tictactoescribd.domain.TicTacToeLogic
import com.takehome.tictactoescribd.data.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import kotlin.Throws

class TicTacToeLogicHorizontalCells {
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
    fun returnTrueIfHasThreeSameHorizontalCellsAtRow1() {
        val cell = Cell(user1)
        ticTacToeLogic!!.cells!![0][0] = cell
        ticTacToeLogic!!.cells!![0][1] = cell
        ticTacToeLogic!!.cells!![0][2] = cell
        val hasThreeSameHorizontalCells = ticTacToeLogic!!.hasThreeSameHorizontalCells()
        Assert.assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnFalseIfDoesNotHaveThreeSameHorizontalCells() {
        val cell = Cell(user1)
        val anotherCell = Cell(user2)
        ticTacToeLogic!!.cells!![0][0] = cell
        ticTacToeLogic!!.cells!![0][1] = cell
        ticTacToeLogic!!.cells!![0][2] = anotherCell
        val hasThreeSameHorizontalCells = ticTacToeLogic!!.hasThreeSameHorizontalCells()
        Assert.assertFalse(hasThreeSameHorizontalCells)
    }
}