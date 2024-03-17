package com.example.five.data.local

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        return value.split(",").map { it.trim() } as ArrayList<String>
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        return list.joinToString(",")
    }
}