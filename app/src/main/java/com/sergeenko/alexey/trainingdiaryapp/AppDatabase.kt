package com.sergeenko.alexey.trainingdiaryapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TrainingData::class], version = 1)
@TypeConverters(
    DateConverter::class,
    ExerciseListConvert::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}