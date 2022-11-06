package com.example.nuevaestrategia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val btnFlotante:View=findViewById((R.id.btnFlotante))
        btnFlotante.setOnClickListener{ view->
            //Snackbar.make(view,getString(R.string.mensajebtnflotante),Snackbar.LENGTH_LONG).show()

            val abrirventanawelcome = Intent(this,ToDoActivity::class.java)
            startActivity(abrirventanawelcome)
        }
    }
}