package com.sergeenko.alexey.trainingdiaryapp

import java.io.Serializable
import java.util.*

sealed class UserListState: Serializable{
    object DefaultState: UserListState()
    object LoadingState: UserListState()
    class LoadedState(var list: List<TrainingData>): UserListState()
    object ErrorState: UserListState()
}