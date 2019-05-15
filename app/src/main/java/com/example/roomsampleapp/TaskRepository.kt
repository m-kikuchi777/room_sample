package com.example.roomsampleapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * タスクデータを扱うためのリポジトリ。
 */
class TaskRepository(
    private val coroutineContext: CoroutineContext,
    private val taskDao: TaskDao) {

    /**
     * タスクデータを保存する。
     */
    fun insert(task: Task) = CoroutineScope(coroutineContext).launch {
        taskDao.insert(task)
    }
}