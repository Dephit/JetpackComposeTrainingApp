package com.sergeenko.alexey.trainingdiaryapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.InternalInteropApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalFocus
class MainActivity : AppCompatActivity() {

    private val viewModel: StartViewModel by inject()

    @InternalInteropApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                    topBar = AppBar(),
                    bodyContent = {
                        ConstraintLayout(
                                Modifier.fillMaxSize()
                                        .background(color = colorResource(id = R.color.teal_700))
                        ) {
                            manageScreenByState()
                            showDialog()
                            addTrainingButton()
                        }
                    }
            )
        }
    }

    @Composable
    private fun ConstraintLayoutScope.addTrainingButton() {
            FloatingActionButton(
                    modifier = constrainInParentByBias(
                            horizontalBias = 1f, verticalBias = 1f
                    ).padding(end = 5.dp, bottom = 5.dp).preferredSize(50.dp),
                    elevation = 10.dp,
                    onClick = { showAlertDialog() },
                    backgroundColor = colorResource(R.color.teal_200)
            ) {
                Image(asset = vectorResource(id = R.drawable.ic_baseline_add_24))
            }

    }

    private fun showAlertDialog() {
        viewModel.changeDissmisState(true)
    }

    @Composable
    private fun ConstraintLayoutScope.manageScreenByState() {
        with(viewModel){
            when (globalState.value) {
                is UserListState.DefaultState -> DefaultState { loadTrainingList() }
                is UserListState.LoadedState -> UserList().trainingList(
                        data = (globalState.value as UserListState.LoadedState).list,
                        state = globalState
                )
                is UserListState.LoadingState -> Splash()
                UserListState.ErrorState -> ErrorState { loadTrainingList() }
            }
        }
    }

    @Composable
    private fun showDialog() {
        if (viewModel.dismissState.value){
            AddTrainingDialog(
                    onSuccess = { data-> addTrainingData(data)} ,
                    onClose = closeAlertDialog()
            )
        }
    }

    private fun addTrainingData(trainigData: TrainingData?) {
        viewModel.addTraining(trainigData)
    }

    private fun closeAlertDialog(): () -> Unit = { viewModel.changeDissmisState(false) }

}

fun Calendar.getFormattedDate(): String{
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.CANADA)
    return dateFormat.format(time)
}

fun ConstraintLayoutScope.constrainInParentByBias(
        horizontalBias: Float, verticalBias: Float
): Modifier {
    return Modifier.constrainAs(createRef()){
        linkTo(start = parent.start, end = parent.end, bias = horizontalBias)
        linkTo(top = parent.top, bottom = parent.bottom, bias = verticalBias)
    }
}