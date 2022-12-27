package uz.icebergsoft.mobilenews.domain.data.entity.pagination

data class PageCountPaginationData<T>(
    val data: List<T>,
    val page: Int,
    val perPage: Int
)