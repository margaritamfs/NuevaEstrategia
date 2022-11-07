package com.example.nuevaestrategia.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDaD {
    @Query("SELECT * FROM ToDo")
    suspend fun getAllTask():List<ToDo>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(tas : ToDo) : Long
    @Update
    suspend fun updateTask(task : ToDo)
    @Delete
    suspend fun deleteTask(Task : ToDo)
}