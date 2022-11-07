package com.example.nuevaestrategia.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(ToDo::class), version = 1)

abstract class ToDoDataBase : RoomDatabase(){
    abstract fun todoDao() : ToDoDaD
    companion object{
        @Volatile
        private var INSTANCE : ToDoDataBase?=null
        fun getDatabase(context: Context) : ToDoDataBase {
            return INSTANCE ?: synchronized(this){
                val instance=Room.databaseBuilder(context.applicationContext,
                ToDoDataBase::class.java,
                "ToDoDatabase").build()
                INSTANCE = instance
                instance
            }
        }

    }
}