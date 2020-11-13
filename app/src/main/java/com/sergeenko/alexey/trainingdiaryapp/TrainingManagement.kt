package com.sergeenko.alexey.trainingdiaryapp

interface TrainingManagement {
    fun setName(string: String)
    fun setDate(string: String)
    fun setComment(string: String)
    fun setExerciseList(list: List<Exercise>)
}
