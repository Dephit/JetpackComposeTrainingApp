package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.android.ext.android.inject

open class BaseModel(application: Application): AndroidViewModel(application), LifecycleObserver{

    internal val app = (application as App)

}
