package com.takehome.tictactoescribd.domain

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takehome.tictactoescribd.data.Cell
import com.takehome.tictactoescribd.data.User

class TicTacToeViewModel : ViewModel() {
    var cells: ObservableArrayMap<String, String>? = null
    private var ticTacToeLogic: TicTacToeLogic? = null
    fun init (user1: String?, user2: String?) {
        ticTacToeLogic = TicTacToeLogic(user1, user2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int) {
        if (ticTacToeLogic!!.cells!!.get(row).get(column) == null) {
            ticTacToeLogic!!.cells!!.get(row)[column] = Cell(ticTacToeLogic!!.currentUser)
            cells!!.put(stringFromNumbers(row, column), ticTacToeLogic!!.currentUser!!.value)
            if (ticTacToeLogic!!.hasGameEnded()) ticTacToeLogic!!.reset() else ticTacToeLogic!!.switchPlayer()
        }
    }

    fun stringFromNumbers(vararg numbers: Int): String {
        val sNumbers = StringBuilder()
        for (number in numbers) sNumbers.append(number)
        return sNumbers.toString()
    }

    val winner: MutableLiveData<User?>
        get() = ticTacToeLogic!!.winner
}
