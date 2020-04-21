package com.mohamed.halim.essa.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mohamed.halim.essa.todolist.R
import com.mohamed.halim.essa.todolist.adapters.TaskAdapter
import com.mohamed.halim.essa.todolist.data.TaskViewModel

class TasksFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
        val tasksListView: ListView = rootView.findViewById(R.id.tasks_list);
        Log.e("task", "log test")

        val viewModel: TaskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(TaskViewModel::class.java)
        val adapter = TaskAdapter(
            requireContext(),
            mutableListOf()
        )
        tasksListView.adapter = adapter;
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                adapter.clear()
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }

        })

        tasksListView.setOnItemLongClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            val bundle: Bundle = bundleOf(
                "task_title" to adapter.getItem(position)!!.title,
                "priority" to adapter.getItem(position)!!.priority,
                "task_id" to adapter.getItem(position)!!.id
            )
            navController.navigate(R.id.action_tasksFragment_to_addTaskFragment, bundle)
            true
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val fab = view.findViewById(R.id.fab_button) as FloatingActionButton
        fab.setOnClickListener {
            navController.navigate(R.id.action_tasksFragment_to_addTaskFragment, null)
        }
    }

}
