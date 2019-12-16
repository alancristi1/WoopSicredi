package com.alan.woopsicredi.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alan.woopsicredi.models.ObjectEvent

@Dao
interface EventDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(event : List<ObjectEvent>)

    @Query("SELECT * FROM event")
    fun all() : List<ObjectEvent>

    @Query("SELECT * FROM event WHERE id = :id")
    fun getEvent(id : String) : ObjectEvent
}