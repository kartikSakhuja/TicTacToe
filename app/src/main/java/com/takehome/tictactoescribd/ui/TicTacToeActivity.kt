package com.takehome.tictactoescribd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.takehome.tictactoescribd.R
import com.takehome.tictactoescribd.data.User
import com.takehome.tictactoescribd.databinding.TicTacToeActivityBinding
import com.takehome.tictactoescribd.domain.TicTacToeViewModel

class TicTacToeActivity : AppCompatActivity() {
  
    private val GAME_START_DIALOG_TAG = "game_dialog_tag"
    private val GAME_FINISH_DIALOG_TAG = "game_finish_dialog_tag"
    private val NO_WINNER = "No winner (board is full and nobody got three in a row)"
    private var ticTacToeViewModel: TicTacToeViewModel? = null
    private lateinit var activityGameBinding: TicTacToeActivityBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        promptForPlayers()
    }

    fun promptForPlayers() {
        val dialog: GameStartDialog = GameStartDialog.newInstance(this)
        dialog.setCancelable(false)
        dialog.show(
            supportFragmentManager,
            GAME_START_DIALOG_TAG)
    }

    fun onPlayersSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }

    private fun initDataBinding(player1: String, player2: String) {

        activityGameBinding =
            DataBindingUtil.setContentView(this, R.layout.tic_tac_toe_activity)
        ticTacToeViewModel = ViewModelProvider(this).get(TicTacToeViewModel::class.java)
        ticTacToeViewModel!!.init(player1, player2)
        activityGameBinding.tictactoeViewModel = ticTacToeViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        ticTacToeViewModel!!.winner.observe(this) { winner: User? -> onGameWinnerChanged(winner) }
    }

    fun onGameWinnerChanged(winner: User?) {
        val winnerName: String? =
            if (winner == null || isNullOrEmpty(winner.name)) NO_WINNER else winner.name
        val dialog: GameFinishDialog = GameFinishDialog.newInstance(this, winnerName)
        dialog.isCancelable = false
        dialog.show(
            supportFragmentManager,
            GAME_FINISH_DIALOG_TAG
        )
    }

    fun isNullOrEmpty(value: String?): Boolean {
        return value == null || value.length == 0
    }
}