package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import java.util.*

sealed class TrainingManagementState{
    object EditingState: TrainingManagementState()
    object SavingState: TrainingManagementState()
    object Saved: TrainingManagementState()
}

class AddTrainingViewModel(application: Application) : BaseModel(application), AddClientInterface, TrainingManagement {
    val trainingData = TrainingData()
    val globalState = MutableLiveData<TrainingManagementState>(TrainingManagementState.EditingState)
    private val exercisesState = MutableLiveData<List<Exercise>>(trainingData.exercises)
    private val trainingDao: TrainingDao = app.get()

    override fun addTraining(trainigData: TrainingData?) {
        globalState.postValue(TrainingManagementState.SavingState)
        viewModelScope.launch(IO) {
            trainigData?.let { trainingDao.insertTraining(it) }
            globalState.postValue(TrainingManagementState.Saved)
        }
    }


    override fun setName(string: String) {
        trainingData.name = string
    }

    override fun setDate(string: String) {
        trainingData.date = Calendar.getInstance()
    }

    override fun setComment(string: String) {
        trainingData.comment = string
    }

    override fun setExerciseList(list: List<Exercise>) {
        trainingData.exercises = list
        exercisesState.postValue(trainingData.exercises)
    }

    override fun getExercises(): List<Exercise> = trainingData.exercises ?: listOf()

    override fun getExerciseListState(): MutableLiveData<List<Exercise>> = exercisesState

    override fun removeLastExercise() {
        val list = trainingData.exercises?.toMutableList()
        list?.removeLastOrNull()
        setExerciseList(list?.toList() ?: listOf())
    }

    override fun addExercise() {
        val list = trainingData.exercises?.toMutableList() ?: mutableListOf()
        list.add(Exercise(name = "New Exercise"))
        setExerciseList(list.toList())
    }

    override fun updateState() {
        exercisesState.postValue(trainingData.exercises)
    }

}