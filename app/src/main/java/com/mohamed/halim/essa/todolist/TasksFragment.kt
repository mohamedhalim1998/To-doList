package com.mohamed.halim.essa.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 */
class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
        val tasksListView: ListView = rootView.findViewById(R.id.tasks_list);
        val tasks = mutableListOf<Task>()
        tasks.add(Task("task1", TaskPriority.HIGH, true))
        tasks.add(Task("task2", TaskPriority.HIGH, true))
        tasks.add(Task("task3", TaskPriority.HIGH, true))
        tasks.add(Task("task4", TaskPriority.HIGH, true))
        tasks.add(Task("task4", TaskPriority.HIGH, true))
        val adapter = TaskAdapter(context!!, tasks)
        tasksListView.adapter = adapter;
        return rootView
    }

}
