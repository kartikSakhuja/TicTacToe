package com.takehome.tictactoescribd

import com.takehome.tictactoescribd.data.Cell
import com.takehome.tictactoescribd.domain.TicTacToeLogic
import com.takehome.tictactoescribd.data.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import kotlin.Throws

class TicTacToeLogicDiagonalCells {
    private var ticTacToeLogic: TicTacToeLogic? = null
    private var player: User? = null
    private var anotherPlayer: User? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        ticTacToeLogic = TicTacToeLogic("Kartik", "CPU")
        player = ticTacToeLogic!!.user1
        anotherPlayer = ticTacToeLogic!!.user2
    }

    @Test
    @Throws(Exception::class)
    fun returnTrueIfHasThreeSameDiagonalCellsFromLeft() {
        val cell = Cell(player)
        ticTacToeLogic!!.cells!![0][0] = cell
        ticTacToeLogic!!.cells!![1][1] = cell
        ticTacToeLogic!!.cells!![2][2] = cell
        val hasThreeSameDiagonalCells = ticTacToeLogic!!.hasThreeSameDiagonalCells()
        Assert.assertTrue(hasThreeSameDiagonalCells)
    }

    @Test
    @Throws(Exception::class)
    fun returnFalseIfDoesNotHaveThreeSameDiagonalCells() {
        val cell = Cell(player)
        val anotherCell = Cell(anotherPlayer)
        ticTacToeLogic!!.cells!![0][2] = cell
        ticTacToeLogic!!.cells!![1][1] = cell
        ticTacToeLogic!!.cells!![2][0] = anotherCell
        val hasThreeSameDiagonalCells = ticTacToeLogic!!.hasThreeSameDiagonalCells()
        Assert.assertFalse(hasThreeSameDiagonalCells)
    }
}