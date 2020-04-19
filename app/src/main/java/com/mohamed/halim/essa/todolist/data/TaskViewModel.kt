package com.mohamed.halim.essa.todolist.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mohamed.halim.essa.todolist.data.Task
import com.mohamed.halim.essa.todolist.data.TaskDatabase

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val tasks : LiveData<List<Task>>
    private val db : TaskDatabase
    init {
        db = TaskDatabase.getDatabaseInstance(application.applicationContext)
        tasks = db.taskDao().loadAllTasks()
    }
}