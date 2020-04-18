package com.mohamed.halim.essa.todolist

data class Task(var title : String ,var priority : TaskPriority , var done : Boolean)
enum class TaskPriority{
    HIGH , NORMAL , LOW
}