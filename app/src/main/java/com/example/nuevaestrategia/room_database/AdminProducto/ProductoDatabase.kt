package com.example.nuevaestrategia.room_database.AdminProducto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Producto::class], version = 1)

abstract class ProductoDatabase : RoomDatabase(){
    abstract fun productos(): ProductosDao
    companion object{
        @Volatile
        private var INSTANCE: ProductoDatabase?=null
        fun getDatabase(context: Context):ProductoDatabase{
            val tempInnstance = INSTANCE
            if(tempInnstance!=null){
                return tempInnstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDatabase::class.java,
                    "Producto_database_Estrategie"
                ).build()
                INSTANCE= instance
                return instance
            }
        }
    }

}