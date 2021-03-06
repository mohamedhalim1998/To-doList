package com.mohamed.halim.essa.todolist.data

import android.content.Context
import androidx.room.*
import androidx.room.Room.databaseBuilder

@Database(entities = arrayOf(Task::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao;

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getDatabaseInstance(context: Context): TaskDatabase {
            val temp =
                INSTANCE
            if (temp != null) {
                return temp;
            }
            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java, "tasks.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
