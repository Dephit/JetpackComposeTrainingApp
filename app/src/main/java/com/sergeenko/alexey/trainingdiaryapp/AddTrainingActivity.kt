package com.sergeenko.alexey.trainingdiaryapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.viewModelScope
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_add_training.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.util.*

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
                    val long = showCalendar(supportFragmentManager = supportFragmentManager)
                    withContext(Main){
                        training_date.editText!!.setText(setDate(long))
                    }
                }
            }
        }
        training_comment_input.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.setComment(text.toString())
        }
    }

    fun addTraining(view: View){
        viewModel.addTraining(viewModel.trainingData)
        onBackPressed()
    }
}

fun Calendar.getDateFromLong(long: Long): Calendar {
    val date = Date()
    date.time = long
    time = date
    return this
}

