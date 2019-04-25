package com.example.roomsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    // データベース。
    private val taskDatabase by lazy {
        TaskRoomDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeButton.setOnClickListener {
            onStoreButtonClicked()
        }

        updateButton.setOnClickListener {
            val getTaskList = CoroutineScope(Dispatchers.IO).async {
                taskDatabase.taskDao().getAllTasks()
            }

            CoroutineScope(Dispatchers.Main).launch {
                createListAdapter(getTaskList.await())
            }
        }
    }

    /**
     * ボタンタップを知らせる。
     */
    private fun onStoreButtonClicked() {
        val title = textInputEditText.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            taskDatabase.taskDao().insert(Task(title))
        }
    }

    /**
     * リストビューを更新する。
     */
    private fun updateListView(adapter: ListAdapter) {
        taskListView.adapter = adapter
    }

    private fun createListAdapter(taskList: List<Task>) {
        val listAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        taskList.forEach {
            listAdapter.add(it.title)
        }

        updateListView(listAdapter)
    }
}
