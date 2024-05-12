package com.example.my_to_do_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_to_do_2.TaskAdapter
import com.example.myto_do.TaskDatabaseHelper

class DisplayActivity:AppCompatActivity() {

    private lateinit var db : TaskDatabaseHelper
    private lateinit var taskAdapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_activity)

        val addbtn :Button = findViewById<Button>(R.id.addbtn)

        db = TaskDatabaseHelper(this)
        taskAdapter = TaskAdapter(db.getAllTasks(),this)

        val displaytaskView : RecyclerView = findViewById(R.id.taskrecyclerview)
        displaytaskView.layoutManager = LinearLayoutManager(this)

        displaytaskView.adapter = taskAdapter;


        addbtn.setOnClickListener{
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }


    }

    override   fun onResume() {
        super.onResume()
        taskAdapter. refreshdata(db.getAllTasks())
    }
}