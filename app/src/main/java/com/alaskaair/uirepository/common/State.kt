package com.alaskaair.uirepository.common

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
}
