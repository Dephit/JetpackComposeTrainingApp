package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import java.util.*

class MainScreenViewModel(application: Application) : BaseModel(application) {
    val globalState = MutableLiveData<UserListState>()
    private val trainingDao: TrainingDao = app.get()
    var  trainingList: List<TrainingData>? = listOf()

    /*fun loadTrainingList() {
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
    }*/

    @Composable
    fun observeOnLiveData(): State<List<TrainingData>> = trainingDao.getTrainingsLiveData().observeAsState(initial = trainingList!!)


    fun loadTrainingList() {
        globalState.postValue(UserListState.LoadingState)
        viewModelScope.launch(IO) {
            trainingList = trainingDao.getTrainings()
            globalState.postValue(UserListState.LoadedState(trainingList!!))
        }
    }

    fun setErrorState() {
        globalState.postValue(UserListState.ErrorState)
    }

}

