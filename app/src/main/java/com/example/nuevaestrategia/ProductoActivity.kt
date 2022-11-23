package com.example.nuevaestrategia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.nuevaestrategia.room_database.AdminProducto.Producto
import kotlinx.android.synthetic.main.activity_producto.*

class ProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        val producto = intent.getSerializableExtra("producto") as Producto
        textViewNombreAP.text= producto.nombre
        textViewPrecioAP.text= "$${producto.precio}"
        textViewDescripcionAP.text= producto.descripcion
        imageViewPA.setImageResource(producto.imagen)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_item>{}
            R.id.delete_item{}
        }
        return super.onOptionsItemSelected(item)
    }
}