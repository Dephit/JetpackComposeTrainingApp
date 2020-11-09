package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class StartViewModel(application: Application) : BaseModel(application){
    val globalState = mutableStateOf<UserListState>(UserListState.DefaultState)

    fun loadTrainingList() {
        globalState.value = UserListState.LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val data = LinkedList<TrainingData>()
            for (i in 0..1000){
                data.add(
                    TrainingData(
                        name = "squat $i",
                        comment = "it was a good day $i"
                    )
                )
            }
            globalState.value = UserListState.LoadedState(data)
        }
    }
}