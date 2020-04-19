package com.mohamed.halim.essa.todolist.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mohamed.halim.essa.todolist.R
import com.mohamed.halim.essa.todolist.data.Task
import com.mohamed.halim.essa.todolist.data.TaskDatabase
import com.mohamed.halim.essa.todolist.data.TaskPriority
import kotlinx.android.synthetic.main.fragment_add_task.*

class AddTaskFragment : Fragment() {

    private lateinit var taskEditText: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_add_task, container, false)
        prioritySpinner = rootView.findViewById<Spinner>(R.id.priority_spinner)
        taskEditText = rootView.findViewById<EditText>(R.id.task_edit_text)
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_task_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save_task -> {
                val db = TaskDatabase.getDatabaseInstance(requireContext())
                val title: String = taskEditText.text.toString();
                val priority: TaskPriority = getTaskPriority()
                val task = Task(
                    title,
                    priority,
                    false,
                    null
                )
                db.taskDao().insertTask(task)
                navController.navigate(R.id.action_addTaskFragment_to_tasksFragment)
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getTaskPriority(): TaskPriority {
        return when (prioritySpinner.selectedItem) {
            "High" -> TaskPriority.HIGH
            "Normal" -> TaskPriority.NORMAL
            "Low" -> TaskPriority.LOW
            else -> TaskPriority.NORMAL
        }
    }

}
