package com.example.nuevaestrategia

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import java.nio.file.attribute.AclEntry.Builder

class MainActivity : AppCompatActivity() {
    private val GOOGLE_SING_IN = 100
    private var stra_usuario : EditText?=null
    private var txtLogin : EditText?=null
    private var authLayout : LinearLayout?=null
    private var btnGoogle : SignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.miMenu))
        stra_usuario=findViewById(R.id.stra_usuario)
        txtLogin=findViewById(R.id.txtLogin)
        authLayout=findViewById(R.id.authLayout)
        btnGoogle = findViewById(R.id.btnGoogle)
        session()

        loginGoogle()

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

    fun onRegisteremail(view: View) {
        title="Atentificacion"
        if(stra_usuario!!.text.isNotEmpty() && txtLogin!!.text.isNotEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                stra_usuario!!.text.toString(),
                txtLogin!!.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show()
                }else{
                    showAlert()
                }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en su autenticación")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog= builder.create()
        dialog.show()
    }

    private fun showHome(email : String,provider : ProviderType){
        Log.e("MainActivity","showHome")
        val homeIntent= Intent(this, Welcome::class.java)
            .apply {
                putExtra("email", email)
                putExtra("provider",provider.name)
            }
        Log.e("MainActivity","homeIntent")
        startActivity(homeIntent)
    }

    fun onLoginEmail(view: View) {
        title="Autenticacion"
        if(stra_usuario!!.text.isNotEmpty() && txtLogin!!.text.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                stra_usuario!!.text.toString(),
                txtLogin!!.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                  //  Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show()
                    showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                }else{
                    showAlert()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authLayout!!.visibility=View.VISIBLE
    }

    private fun session(){
        val prefs : SharedPreferences= getSharedPreferences(
            getString(R.string.prefs_file),
        Context.MODE_PRIVATE)
        val email: String?=prefs.getString("email",null)
        val provider: String?=prefs.getString("provider",null)
        if(email != null && provider != null){
            authLayout!!.visibility=View.INVISIBLE
            showHome(email,ProviderType.valueOf(provider))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val prefs= getSharedPreferences(
            getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }
    fun loginGoogle(){
        btnGoogle!!.setOnClickListener{
            //Toast.makeText(this,"btngoogle",Toast.LENGTH_LONG).show()
            Log.e("MainActivity","loginGoogle")
            val googleleconf : GoogleSignInOptions=GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient : GoogleSignInClient = GoogleSignIn.getClient(this,googleleconf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("MainActivity","onActivityResult")
        Log.e("MainActivity", "requestCode..$requestCode")
        Log.e("MainActivity", "GOOGLE_SING_IN..$GOOGLE_SING_IN")
        if(requestCode==GOOGLE_SING_IN){
            val task : Task<GoogleSignInAccount> = GoogleSignIn
                .getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
                Log.e("MainActivity","account")
                if(account != null){
                    val credential : AuthCredential=GoogleAuthProvider
                        .getCredential(account.idToken,null)
                    Log.e("MainActivity","account != null")
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                Log.e("MainActivity","isSuccessful")
                                showHome(account.email ?:"",ProviderType.GOOGLE)
                            }else{
                                Log.e("MainActivity","showAlert")
                                showAlert()
                            }
                        }
                }
            }catch (e: ApiException){
                Log.e("MainActivity","ApiException")
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage(e.toString())
                builder.setPositiveButton("Aceptar",null)
                val dialog: AlertDialog= builder.create()
                dialog.show()
            }
        }
    }
}