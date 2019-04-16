package com.example.roomsampleapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Room Databaseに接続するためのDAO。
 */
@Dao
interface TaskDao {

    /**
     * 保存されているすべてのTaskデータを取得する。
     */
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): List<Task>

    /**
     * Taskデータを保存する。
     */
    @Insert
    fun insert(task: Task)
}