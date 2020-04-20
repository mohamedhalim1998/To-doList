package com.mohamed.halim.essa.todolist.data

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TaskExecutor(var diskIO: Executor) {
    companion object {
        private val LOCK = Object()

        @Volatile
        private var INSTANCE: TaskExecutor? = null
        fun getTaskExecutor(): TaskExecutor {
            val temp =
                INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(LOCK) {
                val instance = TaskExecutor(Executors.newSingleThreadExecutor())
                INSTANCE = instance
                return instance
            }
        }
    }
}