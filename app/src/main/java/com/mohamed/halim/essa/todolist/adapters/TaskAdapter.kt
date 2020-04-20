package com.mohamed.halim.essa.todolist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.mohamed.halim.essa.todolist.R
import com.mohamed.halim.essa.todolist.data.Task

class TaskAdapter(context: Context, var tasks: List<Task>) :
    ArrayAdapter<Task>(context, 0, tasks) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val rootView =
            view ?: LayoutInflater.from(context).inflate(R.layout.taks_list_item, parent, false);
        val current = tasks.get(position)
        val taskCheckBox: CheckBox = rootView.findViewById(R.id.task_check_box)
        taskCheckBox.text = current.title

        return rootView
    }

}