package com.mohamed.halim.essa.todolist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task")
class Task(
    var title: String,
    var priority: TaskPriority,
    var done: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Long?
)
@Parcelize
enum class TaskPriority : Parcelable{
    HIGH, NORMAL, LOW
}