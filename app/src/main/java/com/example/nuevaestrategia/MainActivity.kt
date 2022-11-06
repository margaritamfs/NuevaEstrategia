package com.example.nuevaestrategia

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private var stra_usuario : EditText?=null
    private var txtLogin : TextInputEditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.miMenu))
        stra_usuario=findViewById(R.id.stra_usuario)
        txtLogin=findViewById(R.id.txtLogin)

    }

    fun onLogin(botonLogin: View) {

        var usuario:String=stra_usuario!!.text.toString()

        if(usuario=="margaritamfs.forero@gmail.com") {
            if(txtLogin!!.text.toString()=="12345")
                {
                val mensajepositivo={dialog:DialogInterface,which:Int->
                    val abrirventanawelcome = Intent(this,Welcome::class.java)
                    startActivity(abrirventanawelcome)
                }

                    val dialog=AlertDialog.Builder(this)
                        .setTitle(getString(R.string.mensajebienvenida))
                        .setMessage(getString(R.string.nombreusuario)+usuario)
                        .setPositiveButton("ok",mensajepositivo)
                        .create()
                        .show()

                }
            else
                {
                 /* Este es el código por si yo quiero en vez de un mensaje Toast, un mensaje en una ventana

                   val dialog=AlertDialog.Builder(this)
                     .setTitle(getString(R.string.mensajeerror))
                     .setMessage(getString(R.string.errorpassword))
                     .create()
                     .show()*/

                    Toast.makeText(this,getString(R.string.errorpassword),Toast.LENGTH_LONG).show()
                }
        }
        else
        {
            /* Este es el código por si yo quiero en vez de un mensaje Toast, un mensaje en una ventana

              val dialog=AlertDialog.Builder(this)
                .setTitle(getString(R.string.mensajeerror))
                .setMessage(getString(R.string.errorusuario))
                .create()
                .show() */

                Toast.makeText(this,getString(R.string.errorusuario),Toast.LENGTH_LONG).show()

        }
    }

    fun onRegister(view: View) {
        val abrirventanawelcome = Intent(this,RegisterActivity::class.java)
        startActivity(abrirventanawelcome)
    }
}