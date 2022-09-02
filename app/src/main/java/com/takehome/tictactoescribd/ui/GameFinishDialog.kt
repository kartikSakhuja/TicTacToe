package com.takehome.tictactoescribd.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.takehome.tictactoescribd.R
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class GameFinishDialog : DialogFragment() {

    private var rootView: View? = null
    private var activity: TicTacToeActivity? = null
    private var winnerName: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        val alertDialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setCancelable(false)
            .setPositiveButton(R.string.done) { dialog: DialogInterface?, which: Int -> onNewGame() }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        return alertDialog
    }

    private fun initViews() {
        rootView = LayoutInflater.from(context)
            .inflate(R.layout.game_end_dialog, null, false)
        (rootView?.findViewById<View>(R.id.tv_winner) as TextView).text = winnerName
    }

    private fun onNewGame() {
        dismiss()
        activity?.promptForPlayers()
    }

    companion object {
        fun newInstance(activity: TicTacToeActivity?, winnerName: String?): GameFinishDialog {
            val dialog = GameFinishDialog()
            dialog.activity = activity
            dialog.winnerName = winnerName
            return dialog
        }
    }
}