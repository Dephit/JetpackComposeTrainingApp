package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.colorResource
import java.lang.ref.WeakReference

class MainScreen(private val viewModel: MainScreenViewModel, private val mainHandler: WeakReference<MainHandler>): BaseScreen() {

    @ExperimentalFocus
    @Composable
    override fun get(){
        Scaffold(
                topBar = AppBar(),
                bodyContent = {
                    ConstraintLayout(
                            Modifier.fillMaxSize()
                                    .background(color = colorResource(id = R.color.teal_700))
                    ) {
                        mainHandler.get()?.let { handler->
                            manageScreenByState(handler)

                        }
                    }
                }
        )
    }


    @Composable
    private fun ConstraintLayoutScope.manageScreenByState(handler: MainHandler) {
        with(viewModel){
            val state = globalState.observeAsState()

            when (state.value) {
                is UserListState.DefaultState -> DefaultState { loadTrainingList() }
                is UserListState.LoadedState -> {
                    TrainingList(data = observeOnLiveData().value, onClick = { setErrorState() })
                    AddTrainingButton(onClick = { handler.openAddTrainingScreen() })
                }
                is UserListState.LoadingState -> Splash()
                is UserListState.ErrorState -> ErrorState { loadTrainingList() }
            }
        }
    }

}

