package com.mohamed.halim.essa.todolist.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.mohamed.halim.essa.todolist.data.TaskExecutor
import com.mohamed.halim.essa.todolist.data.TaskPriority

class AddTaskFragment : Fragment() {

    private lateinit var taskEditText: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var navController: NavController
    private var editMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        editMode = requireArguments().containsKey("task_title")
        Log.d("task", arguments.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_add_task, container, false)
        prioritySpinner = rootView.findViewById(R.id.priority_spinner)
        taskEditText = rootView.findViewById(R.id.task_edit_text)
        if (editMode) {
            populateUi()
        }
        return rootView
    }

    private fun populateUi() {
        setPrioritySpinnerItem(requireArguments().getParcelable<TaskPriority>("priority"))
        taskEditText.setText(requireArguments().getString("task_title"))
    }

    private fun setPrioritySpinnerItem(taskPriority: TaskPriority?) {
        if (taskPriority != null) {
            when (taskPriority) {
                TaskPriority.HIGH -> prioritySpinner.setSelection(0)
                TaskPriority.NORMAL -> prioritySpinner.setSelection(1)
                TaskPriority.LOW -> prioritySpinner.setSelection(2)
                else -> prioritySpinner.prompt = "Normal"
            }
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val delete = menu.findItem(R.id.action_delete_task)
        if (editMode) {
            delete.setVisible(true)
        } else {
            delete.setVisible(false)
        }
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
                saveTask()
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
                requireActivity().onBackPressed()
                return true
            }
            R.id.action_delete_task -> {
                deleteTask()
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        TaskExecutor.getTaskExecutor().diskIO.execute {
            val id = requireArguments().getLong("task_id")
            val task = TaskDatabase.getDatabaseInstance(requireContext()).taskDao().loadTaskById(id)
            TaskDatabase.getDatabaseInstance(requireContext()).taskDao().deleteTask(task)
        }
    }

    private fun getTaskPriority(): TaskPriority {
        return when (prioritySpinner.selectedItem) {
            "High" -> TaskPriority.HIGH
            "Normal" -> TaskPriority.NORMAL
            "Low" -> TaskPriority.LOW
            else -> TaskPriority.NORMAL
        }
    }

    private fun saveTask() {

        val title: String = taskEditText.text.toString();
        val priority: TaskPriority = getTaskPriority()
        val task = Task(
            title,
            priority,
            false,
            null
        )
        TaskExecutor.getTaskExecutor().diskIO.execute {
            if (!editMode) {
                TaskDatabase.getDatabaseInstance(requireContext()).taskDao().insertTask(task)
            } else {
                task.id = requireArguments().getLong("task_id")
                TaskDatabase.getDatabaseInstance(requireContext()).taskDao().updateTask(task)
            }
        }
    }

}
