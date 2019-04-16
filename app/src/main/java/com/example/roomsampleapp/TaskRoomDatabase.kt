package com.example.roomsampleapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Database
 */
@Database(entities = [Task::class], version = 1)
abstract class TaskRoomDatabase : RoomDatabase() {
    /**
     * TaskEntity用のDao
     */
    abstract fun taskDao(): TaskDao

    companion object {
        // インスタンス
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // INSTANCEがnullの場合はRoomDatabaseを生成する。
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}