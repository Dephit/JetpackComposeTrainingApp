package com.sergeenko.alexey.trainingdiaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.InternalInteropApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import java.lang.ref.WeakReference

@ExperimentalFocus
class AddTrainingActivity : AppCompatActivity(), AddTrainingHandler {

    private val viewModel: AddTrainingViewModel by inject()

    @InternalInteropApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddTrainingScreen(viewModel, WeakReference(this)).get()
        }
    }
}