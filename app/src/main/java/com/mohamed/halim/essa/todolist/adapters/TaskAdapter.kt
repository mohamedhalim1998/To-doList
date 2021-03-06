package com.mohamed.halim.essa.todolist.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mohamed.halim.essa.todolist.R
import com.mohamed.halim.essa.todolist.data.Task
import com.mohamed.halim.essa.todolist.data.TaskDatabase
import com.mohamed.halim.essa.todolist.data.TaskExecutor
import com.mohamed.halim.essa.todolist.data.TaskPriority

class TaskAdapter(context: Context, var tasks: List<Task>) :
    ArrayAdapter<Task>(context, 0, tasks) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val rootView =
            view ?: LayoutInflater.from(context).inflate(R.layout.taks_list_item, parent, false);
        val current = tasks.get(position)
        val taskCheckBox: CheckBox = rootView.findViewById(R.id.task_check_box)
        val taskTitleTextView: TextView = rootView.findViewById(R.id.task_title_tv)
        taskTitleTextView.text = current.title
        taskCheckBox.isChecked = current.done
        taskCheckBox.buttonTintList =
            ColorStateList.valueOf(
                when (current.priority) {
                    TaskPriority.HIGH -> ContextCompat.getColor(context, R.color.high_priority)
                    TaskPriority.NORMAL -> ContextCompat.getColor(context, R.color.normal_priority)
                    TaskPriority.LOW -> ContextCompat.getColor(context, R.color.low_priority)
                }
            )
        if (taskCheckBox.isChecked) {
            taskTitleTextView.paintFlags =
                taskTitleTextView.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
        }
        taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                taskTitleTextView.paintFlags =
                    taskTitleTextView.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
                updateTask(current.id!!, true)
            } else {
                taskTitleTextView.paintFlags =
                    (taskTitleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                updateTask(current.id!!, false)
            }
        }
        return rootView
    }

    private fun updateTask(id: Long, done: Boolean) {
        TaskExecutor.getTaskExecutor().diskIO.execute {
            val taskDao = TaskDatabase.getDatabaseInstance(context).taskDao()
            val task = taskDao.loadTaskById(id)
            task.done = done
            taskDao.updateTask(task)
        }
    }

}