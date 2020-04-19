package com.mohamed.halim.essa.todolist.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromPriority(priority: TaskPriority): Int {
        return when (priority) {
            TaskPriority.HIGH -> 3
            TaskPriority.LOW -> 1
            TaskPriority.NORMAL -> 2
        }
    }

    @TypeConverter
    fun toPriority(i: Int): TaskPriority {
        return when (i) {
            1 -> TaskPriority.LOW
            2 -> TaskPriority.NORMAL
            3 -> TaskPriority.HIGH
            else -> TaskPriority.NORMAL
        }
    }
}