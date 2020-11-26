package com.sergeenko.alexey.trainingdiaryapp

import androidx.lifecycle.MutableLiveData
import java.util.*

interface TrainingManagement {
    fun setName(string: String)
    fun setDate(string: Calendar)
    fun setDate(string: Long): String
    fun setComment(string: String)
    fun setExerciseList(list: List<Exercise>)
    fun getExercises(): List<Exercise>
    fun getExerciseListState(): MutableLiveData<List<Exercise>>
    fun removeLastExercise()
    fun addExercise()
    fun updateState()
}
