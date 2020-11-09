package com.sergeenko.alexey.trainingdiaryapp

import java.util.*

sealed class UserListState{
    object DefaultState: UserListState()
    object LoadingState: UserListState()
    class LoadedState(var list: LinkedList<TrainingData>): UserListState()
    object ErrorState: UserListState()
}