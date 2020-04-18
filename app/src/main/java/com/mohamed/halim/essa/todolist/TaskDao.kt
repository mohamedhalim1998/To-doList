package com.mohamed.halim.essa.todolist

import androidx.room.*
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun loadAllTasks() : List<Task>;
    @Query("SELECT * FROM task WHERE id = :id")
    fun loadTaskById(id : Long) : Task;
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: Task)
    @Insert
    fun insertTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
}