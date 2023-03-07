package com.amg.openlist.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.amg.openlist.R

class DialogMessage(private val listener: DialogInterface.OnClickListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(getString(R.string.text_network_message))
                .setPositiveButton("Retry", listener)
            builder.create()
        } ?: throw IllegalStateException("Activity not found")
    }

}