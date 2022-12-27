package uz.icebergsoft.mobilenews.domain.data.entity.pagination

data class LimitOffsetPaginationData<T>(
    val data: List<T>,
    val limit: Int,
    val offset: Int
)