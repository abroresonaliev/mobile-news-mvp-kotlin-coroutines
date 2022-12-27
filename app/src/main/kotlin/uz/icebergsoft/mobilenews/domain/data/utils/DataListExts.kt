package uz.icebergsoft.mobilenews.domain.data.utils

import ru.surfstudio.android.easyadapter.pagination.PaginationState
import ru.surfstudio.android.easyadapter.pagination.PaginationState.COMPLETE
import ru.surfstudio.android.easyadapter.pagination.PaginationState.READY
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.LimitOffsetPaginationData
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.PageCountPaginationData
import ru.surfstudio.android.datalistlimitoffset.domain.datalist.DataList as LimitOffsetDataList
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList as PageCountDataList

val <T>PageCountDataList<out T>.state: PaginationState
    get() = if (this.isEmpty() && this.canGetMore()) READY else COMPLETE

val <T>PageCountDataList<out T>.isFirstPage: Boolean
    get() = this.startPage == 1


fun <T> PageCountPaginationData<T>.mapToDataList(): PageCountDataList<T> =
    PageCountDataList(data, page, perPage)

val <T>PageCountPaginationData<out T>.isFirstPage: Boolean
    get() = this.page == 1


fun <T> LimitOffsetPaginationData<T>.mapToDataList(): LimitOffsetDataList<T> =
    LimitOffsetDataList(data, limit, offset, limit + offset + 1)

val <T>LimitOffsetPaginationData<out T>.isFirstPage: Boolean
    get() = this.offset == 0


fun <T> List<T>.mapToPageCountPaginationData(page: Int, perPage: Int): PageCountPaginationData<T> =
    PageCountPaginationData(data = this, page = page, perPage = perPage)

fun <T> List<T>.mapToLimitOffsetPaginationData(
    limit: Int,
    offset: Int
): LimitOffsetPaginationData<T> =
    LimitOffsetPaginationData(data = this, limit = limit, offset = offset)

