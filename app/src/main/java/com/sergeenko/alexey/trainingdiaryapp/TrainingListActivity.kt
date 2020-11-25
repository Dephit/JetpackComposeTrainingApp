package com.sergeenko.alexey.trainingdiaryapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_training_list.*
import kotlinx.android.synthetic.main.training_item.view.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class TrainingListActivity : AppCompatActivity(){

    private val viewModel: MainScreenViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_list)
        showList()
        /*with(viewModel){
            globalState.observe(this@TrainingListActivity, {
                when (it) {
                    is UserListState.DefaultState -> { loadTrainingList() }
                    is UserListState.LoadedState -> showList()
                    is UserListState.LoadingState -> {}
                    is UserListState.ErrorState ->  {}
                }
            })
        }*/
    }

    fun addTraining(view: View){
        startActivity(
            Intent(applicationContext, AddTrainingActivity::class.java)
        )
    }

    private fun showList() {
        viewModel.getListObservable().observe(this, {
            val viewManager = LinearLayoutManager(applicationContext)
            val viewAdapter = TrainingListAdapter.getAdapter(it)
            val animation = AnimationUtils.loadLayoutAnimation(applicationContext, R.anim.anim)
            with(training_list){
                layoutAnimation = animation
                layoutManager = viewManager
                adapter = viewAdapter
                setHasFixedSize(true)
                setItemViewCacheSize(20)
            }
        })
    }

}

class TrainingListAdapter: RecyclerView.Adapter<TrainingViewHolder>(){

    companion object{
        fun getAdapter(trainingList: List<TrainingData>): TrainingListAdapter {
            val adapter = TrainingListAdapter()
            adapter.setList(trainingList)
            return adapter
        }
    }

    private fun setList(_trainingList: List<TrainingData>) {
        trainingList = _trainingList
    }

    private var trainingList: List<TrainingData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return TrainingViewHolder(inflater.inflate(R.layout.training_item, parent, false))
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(trainingList[position])
    }

    override fun getItemCount(): Int = trainingList.size


}

class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(trainingData: TrainingData) {
        with(itemView){
            name.text =  trainingData.name
            comment.text = trainingData.comment
            date.text = trainingData.date?.getFormattedDate()
        }
    }

}

fun Calendar.getFormattedDate(): String{
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.CANADA)
    return dateFormat.format(time)
}

