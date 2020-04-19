package com.mohamed.halim.essa.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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
        // insertDummyValues()
        val viewModel: TaskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
        ).get(TaskViewModel::class.java)
        val adapter = TaskAdapter(context!!, mutableListOf())
        tasksListView.adapter = adapter;
        viewModel.tasks.observe(this, Observer { tasks ->
            tasks?.let {
                adapter.clear()
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }

        })
        //insertDummyValues()
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
