package com.takehome.tictactoescribd.ui

import android.app.AlertDialog
import android.app.Dialog
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import android.os.Bundle
import com.takehome.tictactoescribd.R
import android.content.DialogInterface
import android.view.LayoutInflater
import android.text.TextWatcher
import android.text.Editable
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.DialogFragment

class GameStartDialog : DialogFragment() {

    private var user1Layout: TextInputLayout? = null
    private var user2Layout: TextInputLayout? = null
    private var user1EditText: TextInputEditText? = null
    private var user2EditText: TextInputEditText? = null
    private var user1: String? = null
    private var user2: String? = null
    private var rootView: View? = null
    private var activity: TicTacToeActivity? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        initViews()
        val alertDialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setTitle(R.string.game_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.done, null)
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener { dialog: DialogInterface? -> onDialogShow(alertDialog) }
        return alertDialog
    }

    private fun initViews() {
        rootView = LayoutInflater.from(context)
            .inflate(R.layout.game_begin_dialog, null, false)
        user1Layout = rootView?.findViewById(R.id.layout_player1)
        user2Layout = rootView?.findViewById(R.id.layout_player2)
        user1EditText = rootView?.findViewById(R.id.et_player1)
        user2EditText = rootView?.findViewById(R.id.et_player2)
        addTextWatchers()
    }

    private fun onDialogShow(dialog: AlertDialog) {
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener { v: View? -> onDoneClicked() }
    }

    private fun isAValidName(layout: TextInputLayout?, name: String?): Boolean {
        if (TextUtils.isEmpty(name)) {
            layout!!.isErrorEnabled = true
            layout.error = getString(R.string.game_dialog_empty_name)
            return false
        }
        if (user1 != null && user2 != null && user1.equals(user2, ignoreCase = true)) {
            layout!!.isErrorEnabled = true
            layout.error = getString(R.string.game_dialog_same_names)
            return false
        }
        layout!!.isErrorEnabled = false
        layout.error = ""
        return true
    }

    private fun onDoneClicked() {
        if (isAValidName(user1Layout, user1) and isAValidName(user2Layout, user2)) {
            activity?.onPlayersSet(user1!!, user2!!)
            dismiss()
        }
    }

    private fun addTextWatchers() {
        user1EditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                user1 = s.toString()
            }
        })
        user2EditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                user2 = s.toString()
            }
        })
    }

    companion object {
        fun newInstance(activity: TicTacToeActivity?): GameStartDialog {
            val dialog = GameStartDialog()
            dialog.activity = activity
            return dialog
        }
    }
}