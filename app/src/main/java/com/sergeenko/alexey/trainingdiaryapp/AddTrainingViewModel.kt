package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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
    private val trainingDao: TrainingDao = app.get()

    override fun addTraining(trainigData: TrainingData?) {
        globalState.postValue(TrainingManagementState.SavingState)
        viewModelScope.launch(Dispatchers.IO) {
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
    }
}