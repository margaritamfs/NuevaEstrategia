package com.example.nuevaestrategia.room_database.repository

import com.example.nuevaestrategia.room_database.ToDo
import com.example.nuevaestrategia.room_database.ToDoDaD

class ToDoRepository (private val toDoDaD: ToDoDaD){

    suspend fun getAllTasks(): List<ToDo>{
        return toDoDaD.getAllTask()
    }

    suspend fun insertTask(toDo: ToDo): Long {
        return toDoDaD.insertTask(toDo)
    }

    suspend fun insertTasks(toDo: List<ToDo>?): List<Long> {
        return toDoDaD.insertTasks(toDo)
    }
}