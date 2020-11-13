package com.sergeenko.alexey.trainingdiaryapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.InternalInteropApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalFocus
class MainActivity : AppCompatActivity(), MainHandler {

    private val viewModel: MainScreenViewModel by inject()

    @ExperimentalMaterialApi
    @InternalInteropApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel, WeakReference(this)).get()
            setViewModel(savedInstanceState)
        }
    }

    private fun setViewModel(savedInstanceState: Bundle?) {
        with(viewModel){
            trainingList = TrainingListConvert()
                    .stringToSomeObjectList(
                            savedInstanceState?.getString("trainingList")
                    )
            globalState.postValue(
                    savedInstanceState?.get("viewModelState") as UserListState?
                            ?: UserListState.DefaultState)
            if(trainingList!!.isEmpty()){
                loadTrainingList()
            }
        }
    }

    override fun openAddTrainingScreen() {
        startActivity(Intent(this, AddTrainingActivity::class.java))
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("viewModelState", viewModel.globalState.value)
        outState.putString("trainingList", TrainingListConvert().someObjectListToString(viewModel.trainingList))
        super.onSaveInstanceState(outState)
    }

}

fun Calendar.getFormattedDate(): String{
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.CANADA)
    return dateFormat.format(time)
}

fun ConstraintLayoutScope.constrainInParentByBias(horizontalBias: Float, verticalBias: Float): Modifier {
    return Modifier.constrainAs(createRef()){
        linkTo(start = parent.start, end = parent.end, bias = horizontalBias)
        linkTo(top = parent.top, bottom = parent.bottom, bias = verticalBias)
    }
}


