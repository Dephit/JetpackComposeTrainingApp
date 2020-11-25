/*
package com.sergeenko.alexey.trainingdiaryapp.Compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.ExperimentalFocus
import com.sergeenko.alexey.trainingdiaryapp.*
import java.lang.ref.WeakReference

class AddTrainingScreen(private val viewModel: AddTrainingViewModel, private val handler: WeakReference<AddTrainingHandler>): BaseScreen() {

    @ExperimentalFocus
    @Composable
    override fun get(){
        Scaffold(
                topBar = AppBar(tittle = "Add Client"),
                bodyContent = {
                    body()
                },
                bottomBar = {
                    Row{
                        TextButton(onClick = {
                            addTrainingData(viewModel.trainingData)
                        }) {
                            Text(text = "Ok")
                        }
                        TextButton(onClick = {
                            handler.get()?.onBackPressed()
                        }) { Text(text = "Close") }
                    }
                }
        )
    }

    @ExperimentalFocus
    @Composable
    private fun body(){
        ConstraintLayout {
            when(viewModel.globalState.observeAsState().value){
                TrainingManagementState.EditingState -> AddTrainingDialogDody(viewModel)
                TrainingManagementState.SavingState -> Splash()
                TrainingManagementState.Saved -> { handler.get()?.onBackPressed() }
            }
        }
    }

    private fun addTrainingData(trainingData: TrainingData?) {
        viewModel.addTraining(trainingData)
    }
}
*/
