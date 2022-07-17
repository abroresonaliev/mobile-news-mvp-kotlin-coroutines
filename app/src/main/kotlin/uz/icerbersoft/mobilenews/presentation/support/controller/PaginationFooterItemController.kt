package uz.icerbersoft.mobilenews.presentation.support.controller

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.databinding.ViewHolderPaginationFooterBinding

class PaginationFooterItemController(
    private val onLoadMoreClicked: () -> Unit,
) : EasyPaginationAdapter.BasePaginationFooterController<PaginationFooterItemController.Holder>() {

    override fun createViewHolder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener,
    ): Holder {
        return Holder(parent, listener)
    }

    inner class Holder(
        parent: ViewGroup,
        listener: EasyPaginationAdapter.OnShowMoreListener,
    ) : EasyPaginationAdapter.BasePaginationFooterHolder(
        parent,
        R.layout.view_holder_pagination_footer
    ) {
        private val binding = ViewHolderPaginationFooterBinding.bind(itemView)

        init {
            with(binding) {
                paginationFooterText.setOnClickListener { onLoadMoreClicked.invoke() }
//                showMoreTv.setOnClickListener { listener.onShowMore() }
                paginationFooterProgress.visibility = GONE
                paginationFooterText.visibility = GONE
            }
        }

        override fun bind(state: PaginationState) {
            //для пагинации на StaggeredGrid
            if (itemView.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                itemView.updateLayoutParams<StaggeredGridLayoutManager.LayoutParams> {
                    isFullSpan = true
                }
            }
            with(binding) {
                when (state) {
                    PaginationState.READY -> {
                        paginationFooterProgress.visibility = VISIBLE
                        paginationFooterText.visibility = GONE
                    }
                    PaginationState.COMPLETE -> {
                        paginationFooterProgress.visibility = GONE
                        paginationFooterText.visibility = GONE
                    }
                    PaginationState.ERROR -> {
                        paginationFooterProgress.visibility = GONE
                        paginationFooterText.visibility = VISIBLE
                    }
                    else -> throw IllegalArgumentException("unsupported state: $state")
                }
            }
        }
    }
}