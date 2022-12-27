package uz.icebergsoft.mobilenews.domain.data.utils

import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.easyadapter.pagination.PaginationState.COMPLETE
import ru.surfstudio.android.easyadapter.pagination.PaginationState.READY
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.PaginationData

val <T>DataList<out T>.state: PaginationState
    get() = if (this.isEmpty() && this.canGetMore()) READY else COMPLETE

val <T>DataList<out T>.isFirstPage: Boolean
    get() = this.startPage == 1


fun <T> PaginationData<T>.mapToDataList(): DataList<T> =
    DataList(data, page, perPage)

val <T>PaginationData<out T>.isFirstPage: Boolean
    get() = this.page == 1


fun <T> List<T>.mapToPaginationData(page: Int, perPage: Int): PaginationData<T> =
    PaginationData(data = this, page = page, perPage = perPage)

fun <T> List<T>.mapToDataList(page: Int): DataList<T> =
    DataList(this, page, this.size)
