package com.sergeenko.alexey.trainingdiaryapp

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Entity
data class TrainingData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @TypeConverters(DateConverter::class)
    var date: Calendar? = null,
    var name: String? = null,
    var comment: String? = null,
    @TypeConverters(ExerciseListConvert::class)
    var exercises: List<Exercise>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            DateConverter().fromString(parcel.readString()),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(Exercise)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(comment)
        parcel.writeTypedList(exercises)
        DateConverter().toString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrainingData> {
        override fun createFromParcel(parcel: Parcel): TrainingData {
            return TrainingData(parcel)
        }

        override fun newArray(size: Int): Array<TrainingData?> {
            return arrayOfNulls(size)
        }
    }
}

@Dao
interface TrainingDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraining(trainingData: TrainingData)

    @Update
    fun updateTraining(trainingData: TrainingData)

    @Delete
    fun deleteClient(trainingData: TrainingData)

    @Query("SELECT * FROM TrainingData")
    fun getTrainings(): List<TrainingData>

    @Query("SELECT * FROM TrainingData")
    fun getTrainingsLiveData(): LiveData<List<TrainingData>>

}
