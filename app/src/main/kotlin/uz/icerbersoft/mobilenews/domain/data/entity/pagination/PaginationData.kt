package uz.icerbersoft.mobilenews.domain.data.entity.pagination

data class PaginationData<T>(
    val page: Int,
    val pageSize: Int,
    val data: List<T>,
)