package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.random.Random

class StartViewModel(application: Application) : BaseModel(application){
    val globalState = mutableStateOf<UserListState>(UserListState.DefaultState)
    val dismissState = mutableStateOf(false)
    val trainingDao: TrainingDao = app.get()

    fun loadTrainingList() {
        globalState.value = UserListState.LoadingState
        viewModelScope.launch(IO) {
            val data = LinkedList<TrainingData>()
            for (i in 0..1000){
                data.add(
                    TrainingData(
                            name = "squat $i",
                            date = Calendar.getInstance(),
                            comment = "it was a good day $i"
                    )
                )
            }
            globalState.value = UserListState.LoadedState(data)
        }
    }

    private fun loadTrainingFromDataBase() {
        globalState.value = UserListState.LoadingState
        viewModelScope.launch(IO) {
            globalState.value = UserListState.LoadedState(trainingDao.getTrainingsLiveData())
        }
    }

    fun changeDissmisState(b: Boolean) {
        dismissState.value = b
    }

    fun addTraining(trainigData: TrainingData?) {
        viewModelScope.launch(IO) {
            trainigData?.let { trainingDao.insertTraining(it) }
            changeDissmisState(false)
            loadTrainingFromDataBase()
        }
    }
}