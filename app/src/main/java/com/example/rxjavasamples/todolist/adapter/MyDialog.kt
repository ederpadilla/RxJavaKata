package com.example.androidtutz.todolistapp.adapter

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.rxjavasamples.R


/**
 * Created by K. A. ANUSHKA MADUSANKA on 12/29/2017.
 */

class MyDialog {

    private val result = 0

    fun openDialog(context: Context, mainTitle: String, title: String, description: String, category: Int) {
        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_demo_mission)
        dialog.setTitle(mainTitle)
        val dialogButtonCancel = dialog.findViewById<Button>(R.id.dialog_cancel)
        val dialogButtonOk = dialog.findViewById<Button>(R.id.dialog_ok)
        val dialogDesc = dialog.findViewById<TextView>(R.id.dialog_message)
        dialogDesc.text = description

        if (category == 0) {

            dialogButtonCancel.setOnClickListener { dialog.dismiss() }

            dialogButtonOk.setOnClickListener { dialog.dismiss() }

            dialog.show()

        } else if (category == 1) {

            dialogButtonOk.text = "OK"

            dialogButtonCancel.setOnClickListener { dialog.dismiss() }


            dialogButtonOk.setOnClickListener { dialog.dismiss() }

            dialog.show()


        } else if (category == 2) {

            dialogButtonOk.text = "OK"

            dialogButtonCancel.setOnClickListener { dialog.dismiss() }


            dialogButtonOk.setOnClickListener { dialog.dismiss() }

            dialog.show()

        }
    }

    fun openConfirmDialog(context: Context, title: String, description: String) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_confirm)
        dialog.setTitle(title)
        val dialogButtonOk = dialog.findViewById<Button>(R.id.dialog_ok)
        dialogButtonOk.text = "OK"
        val dialogDesc = dialog.findViewById<TextView>(R.id.dialog_message)
        dialogDesc.text = description


        dialogButtonOk.setOnClickListener { dialog.dismiss() }

        dialog.show()

    }


    fun openSaveDialog(context: Context, title: String, description: String): Int {

        val dialog = Dialog(context) // Context, this, etc.

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_save)
        dialog.setTitle(title)
        val dialogButtonOk = dialog.findViewById<Button>(R.id.dialog_ok)
        val dialogCancel = dialog.findViewById<Button>(R.id.dialog_cancel)
        dialogButtonOk.text = "OK"
        val dialogDesc = dialog.findViewById<TextView>(R.id.dialog_message)
        dialogDesc.text = description


        dialogButtonOk.setOnClickListener { dialog.dismiss() }


        dialogCancel.setOnClickListener { dialog.dismiss() }

        dialog.show()

        val test : String = "kanskd"

        return result

    }

}
