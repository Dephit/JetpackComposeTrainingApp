package com.sergeenko.alexey.trainingdiaryapp

import android.os.Parcel
import android.os.Parcelable

data class Exercise(
    var name: String? = null,
    val comment: String? = null,
    var sets: List<SetData>? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(SetData)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(comment)
        parcel.writeTypedList(sets)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun setExerciseName(_name: String) {
        name = _name
    }

    fun addSet(set: SetData) {
        if(sets == null)
            sets = mutableListOf()
        val list = sets!!.toMutableList()
        list.add(set)
        sets = list

    }

    fun removeLastSet() {
        if(sets == null)
            sets = mutableListOf()
        val list = sets!!.toMutableList()
        list.removeLastOrNull()
        sets = list
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}
