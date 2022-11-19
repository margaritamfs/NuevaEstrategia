package com.example.nuevaestrategia

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

enum class ProviderType{
    BASIC,
    GOOGLE
}

class Welcome : AppCompatActivity() {
    private var textemail: TextView?=null
    private var textprovedor: TextView?=null
    private var btnLogOut: Button?=null
    private var btnProductoAW: Button?=null
    @SuppressLint("SuspiciousIndentation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        textemail=findViewById(R.id.textemail)
        textprovedor=findViewById(R.id.textprovedor)
        btnProductoAW=findViewById(R.id.btnProductoAW)
        val bundle=intent.extras
        val email=bundle?.getString("email")
        val provide=bundle?.getString("provider")
        textemail!!.text=email
        textprovedor!!.text=provide
        val prefs= getSharedPreferences(
            getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.putString("email",email)
            prefs.putString("provide",provide)
        btnLogOut= findViewById(R.id.buttonCerrar)
        btnLogOut!!.setOnClickListener {
            val prefs= getSharedPreferences(
                getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
           // onBackPressed()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnProductoAW!!.setOnClickListener{
            val intent= Intent(this, ListaProductoActivity::class.java)
            startActivity(intent)
        }


        val btnFlotante:View=findViewById((R.id.btnFlotante))
        btnFlotante.setOnClickListener{ view->
            //Snackbar.make(view,getString(R.string.mensajebtnflotante),Snackbar.LENGTH_LONG).show()

            val abrirventanawelcome = Intent(this,ToDoActivity::class.java)
            startActivity(abrirventanawelcome)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val homeIntent = Intent(this,Welcome::class.java)
            .apply {
                putExtra("email",textemail!!.text.toString())
                putExtra("email",textprovedor!!.text.toString())
            }
        startActivity(homeIntent)
    }
}