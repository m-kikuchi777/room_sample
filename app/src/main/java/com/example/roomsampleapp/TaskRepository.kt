package com.example.roomsampleapp

import androidx.annotation.WorkerThread

/**
 * タスクデータを扱うためのリポジトリ。
 */
class TaskRepository(private val taskDao: TaskDao) {

    /**
     * タスクデータを保存する。
     */
    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)

    }
}