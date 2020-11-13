package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class AddTrainingViewModel(application: Application) : BaseModel(application), AddClientInterface {

    private val trainingDao: TrainingDao = app.get()

    override fun addTraining(trainigData: TrainingData?) {
        viewModelScope.launch(Dispatchers.IO) {
            trainigData?.let { trainingDao.insertTraining(it) }
        }
    }
}