package com.alan.woopsicredi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.models.dao.EventDao

@Database(entities = [ObjectEvent::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}