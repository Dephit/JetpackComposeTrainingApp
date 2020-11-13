package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.viewinterop.viewModel

class AddTrainingScreen(private val viewModel: AddTrainingViewModel): BaseScreen() {

    @ExperimentalFocus
    @Composable
    override fun get(){
        AddTrainingDialogDody(onSuccess = { addTrainingData(trainingData = it) })
    }


    private fun addTrainingData(trainingData: TrainingData?) {
        viewModel.addTraining(trainingData)
    }
}
