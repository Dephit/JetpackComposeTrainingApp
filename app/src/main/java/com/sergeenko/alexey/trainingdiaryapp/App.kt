package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)
            // use modules
            modules(
                roomModule,
                viewModelModule,
                mainModule
            )
        }
    }

    private val viewModelModule = module {
        viewModel {
            MainScreenViewModel(get())
        }
        viewModel {
            AddTrainingViewModel(get())
        }
    }

    private val mainModule = module{
        /*factory(qualifier = named("MainScreen")) {
            MainScreen()
        }
        factory{
            AddTrainingScreen()
        }*/
    }

    private val roomModule = module {
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, "diary_db")
                    .fallbackToDestructiveMigration()
                    .build()
        }
        single {
            (get() as AppDatabase).trainingDao()
        }
    }





}
