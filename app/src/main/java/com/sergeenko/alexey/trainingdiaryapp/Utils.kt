package com.sergeenko.alexey.trainingdiaryapp

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun showCalendar(supportFragmentManager: FragmentManager): Long {
    return suspendCancellableCoroutine{cont->
        val calendar = MaterialDatePicker.Builder
                .datePicker()
                .build()
        calendar.addOnPositiveButtonClickListener {
            cont.resume(it)
            cont.cancel()
        }
        calendar.addOnCancelListener {
            cont.resume(-1)
            cont.cancel()
        }
        calendar.show(supportFragmentManager, "Calendar")
    }
}