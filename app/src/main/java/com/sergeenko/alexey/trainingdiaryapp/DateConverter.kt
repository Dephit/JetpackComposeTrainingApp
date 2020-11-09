package com.sergeenko.alexey.trainingdiaryapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class DateConverter {

    @TypeConverter
    fun toString(date: Calendar?): String? = Gson().toJson(date)
    @TypeConverter
    fun fromString(string: String?): Calendar? = Gson().fromJson(string, Calendar::class.java)
}
