package com.sergeenko.alexey.trainingdiaryapp

import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

data class SetData(
        var weight: Double = 0.0,
        var reps: Int = 0,
        val comment: String? = null,
        val video: MediaStore.Video? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readString(),
            null,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(weight)
        parcel.writeInt(reps)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SetData> {
        override fun createFromParcel(parcel: Parcel): SetData {
            return SetData(parcel)
        }

        override fun newArray(size: Int): Array<SetData?> {
            return arrayOfNulls(size)
        }
    }
}
