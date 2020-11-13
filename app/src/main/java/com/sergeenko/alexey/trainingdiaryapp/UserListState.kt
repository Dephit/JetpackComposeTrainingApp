package com.sergeenko.alexey.trainingdiaryapp

import java.io.Serializable

sealed class UserListState: Serializable{
    object DefaultState: UserListState()
    object LoadingState: UserListState()
    object LoadedState : UserListState()
    object ErrorState: UserListState()
}