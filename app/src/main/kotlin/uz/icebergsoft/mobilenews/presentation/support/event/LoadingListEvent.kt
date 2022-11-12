package uz.icebergsoft.mobilenews.presentation.support.event

sealed class LoadingListEvent<out T> {

    data class SuccessState<out T>(val data: List<T>) : LoadingListEvent<T>()

    object LoadingState : LoadingListEvent<Nothing>()

    object EmptyState : LoadingListEvent<Nothing>()

    data class ErrorState(val message: String?) : LoadingListEvent<Nothing>()
}