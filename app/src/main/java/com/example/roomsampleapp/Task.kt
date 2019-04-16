package com.example.roomsampleapp

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * タスクのデータを格納するテーブルのエンティティ。
 */
@Entity(tableName = "task_table")
data class Task(@PrimaryKey val title: String)