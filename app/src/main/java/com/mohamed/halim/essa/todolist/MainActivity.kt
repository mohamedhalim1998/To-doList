package com.mohamed.halim.essa.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        val tasksFragment = TasksFragment()
        val addTaskFragment = AddTaskFragment()
        val fab = findViewById<FloatingActionButton>(R.id.fab_button)
        fab.setOnClickListener {
            fab.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, addTaskFragment)
                .commit()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, tasksFragment)
            .commit()
    }
}
