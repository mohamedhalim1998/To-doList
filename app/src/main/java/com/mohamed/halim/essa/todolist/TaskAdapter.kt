package com.mohamed.halim.essa.todolist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskAdapter(context: Context, var tasks: List<Task>) :
    ArrayAdapter<Task>(context, 0, tasks) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val rootView =
            view ?: LayoutInflater.from(context).inflate(R.layout.taks_list_item, parent, false);
        val current = tasks.get(position)
        val titleTextView: TextView = rootView.findViewById(R.id.task_title);
        titleTextView.text = current.title;
        return rootView
    }

}