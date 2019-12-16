package com.alan.woopsicredi.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class ObjectEvent(

    @PrimaryKey
    var id: String = "",

    @Ignore
    var cupons: List<Cupon> = mutableListOf(),

    @ColumnInfo(name = "date")
    var date: Long = 0L,

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "image")
    var image: String = "",

    @ColumnInfo(name = "latitude")
    var latitude: Double = 0.0,

    @ColumnInfo(name = "longitude")
    var longitude: Double = 0.0,

    @Ignore
    var people: List<People> = mutableListOf(),

    @ColumnInfo(name = "price")
    var price: Double = 0.0,

    @ColumnInfo(name = "title")
    var title: String = ""
)