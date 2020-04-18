package com.mohamed.halim.essa.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tasksFragment =  TasksFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, tasksFragment).commit()
    }
}