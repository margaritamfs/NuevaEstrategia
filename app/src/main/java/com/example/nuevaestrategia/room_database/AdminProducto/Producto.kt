package com.example.nuevaestrategia.room_database.AdminProducto

import java.io.Serializable

class Producto (
    val nombre : String,
    val precio: Float,
    val descripcion: String,
    val imagen : Int
        ) : Serializable