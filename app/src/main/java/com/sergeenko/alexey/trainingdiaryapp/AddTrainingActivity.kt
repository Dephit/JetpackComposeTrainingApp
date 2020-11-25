package com.sergeenko.alexey.trainingdiaryapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_add_training.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.coroutines.resume

class AddTrainingActivity : AppCompatActivity() {

    val viewModel: AddTrainingViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_training)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        onTextInsert()
    }

    private fun onTextInsert() {
        training_name_input.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setName(text.toString())
        }

        training_date.editText?.setOnClickListener {
            with(viewModel){
                viewModelScope.launch(IO) {
                    val long = showCalendar()
                    if (long != -1L) {
                        withContext(Main){
                            val calendar = Calendar.getInstance()
                            val date = Date()
                            date.time = long
                            calendar.time = date
                            training_date.editText!!.setText(calendar.getFormattedDate())
                            setDate(calendar.getFormattedDate())
                        }
                    }
                }
            }
        }
        training_comment_input.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setComment(text.toString())
        }
    }

    private suspend fun showCalendar(): Long {
        return suspendCancellableCoroutine{cont->
            val calendar = MaterialDatePicker.Builder
                    .datePicker()
                    .build()
            calendar.addOnPositiveButtonClickListener {
                cont.resume(it)
            }
            calendar.addOnCancelListener {
                cont.resume(-1)
            }
            calendar.show(supportFragmentManager, "Calendar")
        }
    }

    fun addTraining(view: View){
        viewModel.addTraining(viewModel.trainingData)
        onBackPressed()
    }
}