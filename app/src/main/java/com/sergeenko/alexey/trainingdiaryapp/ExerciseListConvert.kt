package com.sergeenko.alexey.trainingdiaryapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class ExerciseListConvert {

    @TypeConverter
    fun stringToSomeObjectList(d: String?): List<Exercise>? {
        val data = d ?: return Collections.emptyList()
        if (data == "" || data == "null" || data == "[]" || data.isEmpty()) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Exercise?>?>() {}.type
        return try {
            Gson().fromJson(data, listType)
        }catch (e:Exception){
            Collections.emptyList()
        }
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Exercise>?): String? {
        return Gson().toJson(someObjects)
    }
}

class TrainingListConvert {

    @TypeConverter
    fun stringToSomeObjectList(d: String?): List<TrainingData>? {
        val data = d ?: return Collections.emptyList()
        if (data == "" || data == "null" || data == "[]" || data.isEmpty()) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<TrainingData?>?>() {}.type
        return try {
            Gson().fromJson(data, listType)
        }catch (e:Exception){
            Collections.emptyList()
        }
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<TrainingData>?): String? {
        return Gson().toJson(someObjects)
    }
}