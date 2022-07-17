package uz.icerbersoft.mobilenews.domain.utils

import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.easyadapter.pagination.PaginationState.COMPLETE
import ru.surfstudio.android.easyadapter.pagination.PaginationState.READY
import uz.icerbersoft.mobilenews.domain.data.entity.pagination.PaginationData

val <T>DataList<out T>.state: PaginationState
    get() = if (this.canGetMore()) READY else COMPLETE

fun <T> PaginationData<T>.mapToDataList(): DataList<T> =
    DataList(data, page, pageSize)

fun <T> List<T>.mapToDataList(page: Int): DataList<T> =
    DataList(this, page, this.size)
