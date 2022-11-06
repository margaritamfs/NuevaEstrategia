package com.example.nuevaestrategia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private var edName: EditText?=null
    private var edEmail : EditText?=null
    private val texto_pattern : Pattern=Pattern.compile("[a-zA-Z]*")
    private val email_Pattern: Pattern= Patterns.EMAIL_ADDRESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        edName=findViewById(R.id.edname)
        edEmail=findViewById(R.id.edadress)
    }

    fun onRegister(view: View){
        if(validarformulario()){
            Toast.makeText(this,"ok",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
        }
    }

    private fun validarformulario():Boolean{
        var validate=true
        var name:String= edName!!.text.toString()
        var email:String= edEmail!!.text.toString()

        /*validar name*/

        if(TextUtils.isEmpty(name)){
            edName!!.error="Requerido"
            validate=false
        }else if(!texto_pattern.matcher(name.replace("","")).matches()) {
            edName!!.error = "Nombre invalido"
            validate = false
        }else edName!!.error =null

        /*validar email*/

        if(TextUtils.isEmpty(email)){
            edEmail!!.error="Requerido"
            validate=false
        }else if(!email_Pattern.matcher(email).matches()) {
            edEmail!!.error = "Correo invalido"
            validate = false
        }else edEmail!!.error =null

        return validate
    }
}