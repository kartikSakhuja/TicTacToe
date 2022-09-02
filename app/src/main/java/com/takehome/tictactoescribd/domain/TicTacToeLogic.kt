package com.takehome.tictactoescribd.domain

import androidx.lifecycle.MutableLiveData
import com.takehome.tictactoescribd.data.Cell
import com.takehome.tictactoescribd.data.User

class TicTacToeLogic(playerOne: String?, playerTwo: String?) {

        private val BOARD_SIZE = 3
        var user1: User? = null
        var user2: User? = null
        var currentUser: User? = user1
        var cells: Array<Array<Cell?>>? = null
        var winner: MutableLiveData<User?> = MutableLiveData()


    init {
        cells = Array(BOARD_SIZE) { arrayOfNulls(BOARD_SIZE) }
        user1 = User(playerOne, "X")
        user2 = User(playerTwo, "O")
        currentUser = user1
    }

        fun hasGameEnded(): Boolean {
            if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
                winner.setValue(currentUser)
                return true
            }
            if (isBoardFull()) {
                winner.setValue(null)
                return true
            }
            return false
        }

        fun hasThreeSameHorizontalCells(): Boolean {
            return try {
                for (i in 0 until BOARD_SIZE) if (areEqual(
                        cells!![i][0]!!,
                        cells!![i][1]!!, cells!![i][2]!!
                    )
                ) return true
                true
            } catch (e: NullPointerException) {
                false
            }
        }

        fun hasThreeSameVerticalCells(): Boolean {
            return try {
                for (i in 0 until BOARD_SIZE) if (areEqual(
                        cells!![0][i]!!,
                        cells!![1][i]!!, cells!![2][i]!!
                    )
                ) return true
                true
            } catch (e: NullPointerException) {
                false
            }
        }

        fun hasThreeSameDiagonalCells(): Boolean {
            return try {
                areEqual(
                    cells!![0][0]!!, cells!![1][1]!!,
                    cells!![2][2]!!
                ) ||
                        areEqual(cells!![0][2]!!, cells!![1][1]!!, cells!![2][0]!!)
            } catch (e: NullPointerException) {
                false
            }
        }

        fun isBoardFull(): Boolean {
            for (row in cells!!) for (cell in row) if (cell == null || cell.isEmpty()) return false
            return true
        }

        private fun areEqual(vararg cells: Cell): Boolean {
            if (cells.size == 0) return false
            for (cell in cells) if (false || isNullOrEmpty(cell.user!!.value)) return false
            val comparisonBase = cells[0]
            for (i in 1 until cells.size) if (!comparisonBase.user!!.value.equals(cells[i].user!!.value)) return false
            return true
        }

        fun switchPlayer() {
            currentUser = if (currentUser === user1) user2 else user1
        }

        fun reset() {
            user1 = null
            user2 = null
            currentUser = null
            cells = null
        }

    fun isNullOrEmpty(value: String?): Boolean {
        return value == null || value.length == 0
    }
}