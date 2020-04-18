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
        insertDummyValues()
        val tasks = TaskDatabase.getDatabaseInstance(context!!).taskDao().loadAllTasks()
        val adapter = TaskAdapter(context!!, tasks)

        tasksListView.adapter = adapter;
        return rootView
    }
    private fun insertDummyValues() {
        val db = TaskDatabase.getDatabaseInstance(context!!)
        val task = Task("task 1", TaskPriority.HIGH, false, null);
        for (i in 1..5) {
            db.taskDao().insertTask(task)
        }
    }

}
