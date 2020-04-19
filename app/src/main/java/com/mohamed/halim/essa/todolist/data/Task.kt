package com.mohamed.halim.essa.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
class Task(
    var title: String,
    var priority: TaskPriority,
    var done: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Long?
)

enum class TaskPriority {
    HIGH, NORMAL, LOW
}