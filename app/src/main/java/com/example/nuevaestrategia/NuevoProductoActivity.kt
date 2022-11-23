package com.example.nuevaestrategia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nuevaestrategia.room_database.AdminProducto.Producto
import com.example.nuevaestrategia.room_database.AdminProducto.ProductoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)
        val database = ProductoDatabase.getDatabase(this)
        val dbFirebase = FirebaseFirestore.getInstance()
        buttonNPA.setOnClickListener{
            val nombre = editTextNombreNPA.text.toString()
            val precio = editTextPrecioNPA.text.toString().toFloat()
            val descripcion = editTextDetalleNPA.text.toString()
            val producto = Producto(0, nombre, precio, descripcion, R.drawable.ic_launcher_background)
            CoroutineScope(Dispatchers.IO).launch {
               var result=database.productos().insert(producto)

                dbFirebase.collection("Productos").document(result.toString())
                    .set(hashMapOf("nombre" to nombre, "precio" to precio, "descripcion" to descripcion)
                    )


                this@NuevoProductoActivity.finish()
            }
        }
    }
}