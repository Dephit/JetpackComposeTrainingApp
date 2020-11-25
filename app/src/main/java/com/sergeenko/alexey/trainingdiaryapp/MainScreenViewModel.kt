package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import java.sql.RowId

class MainScreenViewModel(application: Application) : BaseModel(application) {
    val globalState = MutableLiveData<UserListState>(UserListState.DefaultState)
    private val trainingDao: TrainingDao = app.get()
    var  trainingList: List<TrainingData>? = listOf()

    fun getListObservable() = trainingDao.getTrainingsLiveData()

    fun loadTrainingList() {
        globalState.postValue(UserListState.LoadingState)
        viewModelScope.launch(IO) {
            trainingList = trainingDao.getTrainings()
            checkOnState(trainingList!!)
        }
    }

    fun checkOnState(list: List<TrainingData>) {
        globalState.postValue(
                if(list.isEmpty())
                    UserListState.DefaultState
                else
                    UserListState.LoadedState
        )
    }

    fun setErrorState() {
        globalState.postValue(UserListState.ErrorState)
    }

    fun removeTraining(data: TrainingData?) {
        viewModelScope.launch(IO) {
            data?.let { trainingDao.deleteClient(it) }
        }
    }

}

