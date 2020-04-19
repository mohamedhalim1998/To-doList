package com.mohamed.halim.essa.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun loadAllTasks() : LiveData<List<Task>>
    @Query("SELECT * FROM task WHERE id = :id")
    fun loadTaskById(id : Long) : Task;
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: Task)
    @Insert
    fun insertTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
}