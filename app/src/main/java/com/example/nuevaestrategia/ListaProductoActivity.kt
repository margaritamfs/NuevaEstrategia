package com.example.nuevaestrategia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.nuevaestrategia.room_database.AdminProducto.Producto
import com.example.nuevaestrategia.room_database.AdminProducto.ProductoAdaptar
import com.example.nuevaestrategia.room_database.AdminProducto.ProductoDatabase
import kotlinx.android.synthetic.main.activity_lista_producto.*

class ListaProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_producto)

      /*val producto = Producto(0,"tv", 100.0F, "HDMI el mejor TV", R.drawable.ic_baseline_live_tv_24)
        val producto2 = Producto(0,"camara", 5500.0F, "camaraza", R.drawable.ic_baseline_camera_alt_24)
        val producto3 = Producto("cualquier cosa", 550.0F, "otromas", R.drawable.ic_baseline_build_24)
        val listaProducto = listOf(producto,producto2,producto3)*/

        var listaProducto= emptyList<Producto>()
        val database = ProductoDatabase.getDatabase(this)
        database.productos().getAll().observe(
            this, Observer {listaProducto=it
                val adaptar = ProductoAdaptar(this, listaProducto)
                listaP.adapter= adaptar
            }
        )


        listaP.setOnItemClickListener() { parent, view, position, id ->
            val intent= Intent(this, ProductoActivity::class.java)
            intent.putExtra("producto",listaProducto[position])
            startActivity(intent)
        }

        floatingbuttonALP.setOnClickListener{
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }
}