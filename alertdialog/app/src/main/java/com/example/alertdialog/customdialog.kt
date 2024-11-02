package com.example.alertdialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import cn.pedant.SweetAlert.SweetAlertDialog

class customdialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){

            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_sweetdialogbox)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            val dialog = AlertDialog.Builder(this)
            val infalter = this.layoutInflater
            dialog.setView(infalter.inflate(R.layout.customdialog, null))
        dialog.setTitle("Title")
        dialog.setMessage("message")
        dialog.setPositiveButton("yes"){
            dialog,which->
            Toast.makeText(this,"yes",Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("no"){dialog ,which->
            Toast.makeText(this,"no",Toast.LENGTH_SHORT).show()
        }
        dialog.setNeutralButton("cancel"){dialog,which->
            Toast.makeText(this,"neutra",Toast.LENGTH_SHORT).show()
        }
        dialog.create().show()
        }

}