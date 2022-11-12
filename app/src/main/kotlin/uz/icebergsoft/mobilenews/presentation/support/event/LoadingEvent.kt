package uz.icebergsoft.mobilenews.presentation.support.event

sealed class LoadingEvent<out T> {

    data class SuccessState<out T>(val data: T) : LoadingEvent<T>()

    object LoadingState : LoadingEvent<Nothing>()

    data class ErrorState(val message: String?) : LoadingEvent<Nothing>()
}