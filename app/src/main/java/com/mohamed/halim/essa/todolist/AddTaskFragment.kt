package com.mohamed.halim.essa.todolist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Spinner

class AddTaskFragment : Fragment() {

    private lateinit var taskEditText: EditText;
    private lateinit var prioritySpinner: Spinner;
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save_task -> {
                val db = TaskDatabase.getDatabaseInstance(context!!)
                val title: String = taskEditText.text.toString();
                val priority: TaskPriority = getTaskPriority()
                val task = Task(title, priority, false, null);
                db.taskDao().insertTask(task)
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
