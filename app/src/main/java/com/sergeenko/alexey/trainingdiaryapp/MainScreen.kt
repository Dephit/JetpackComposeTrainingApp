package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.colorResource
import java.lang.ref.WeakReference

@ExperimentalMaterialApi
class MainScreen(private val viewModel: MainScreenViewModel, private val mainHandler: WeakReference<MainHandler>): BaseScreen() {

    @ExperimentalFocus
    @Composable
    override fun get(){
        Scaffold(
                topBar = AppBar("Training Diary"),
                bodyContent = {
                    ConstraintLayout(
                            Modifier.fillMaxSize()
                                    .background(color = colorResource(id = R.color.teal_700))
                    ) {
                        mainHandler.get()?.let { handler->
                            manageScreenByState()
                            AddTrainingButton(onClick = { handler.openAddTrainingScreen() })
                        }
                    }
                }
        )
    }


    @Composable
    private fun ConstraintLayoutScope.manageScreenByState() {
        with(viewModel){
            val state = globalState.observeAsState()
            val list = observeOnLiveData().value
            checkOnState(list)
            when (state.value) {
                is UserListState.DefaultState -> DefaultState { loadTrainingList() }
                is UserListState.LoadedState -> TrainingList(data = list, onClick = { setErrorState() }, onSwipe = { removeTraining(it) })
                is UserListState.LoadingState -> Splash()
                is UserListState.ErrorState -> ErrorState { loadTrainingList() }
            }
        }
    }

}

