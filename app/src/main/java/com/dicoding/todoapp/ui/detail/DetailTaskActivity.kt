package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailTaskViewModel
    private lateinit var buttonDel : Button
    private lateinit var inputTitle: TextInputEditText
    private lateinit var inputDesc: TextInputEditText
    private lateinit var inputDueDate : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        inputTitle = findViewById(R.id.detail_ed_title)
        inputDesc = findViewById(R.id.detail_ed_description)
        inputDueDate = findViewById(R.id.detail_ed_due_date)
        buttonDel = findViewById(R.id.btn_delete_task)

        val viewFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewFactory)[DetailTaskViewModel::class.java]
        viewModel.setTaskId(intent.getIntExtra(TASK_ID,0))
        viewModel.task.observe(this,{task->
            if(task!=null){
                inputTitle.setText(task.title)
                inputDesc.setText(task.description)
                inputDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        })
        buttonDel.setOnClickListener {
            viewModel.deleteTask()
        onBackPressed()
        }

        //TODO 11 : Show detail task and implement delete action

    }
}