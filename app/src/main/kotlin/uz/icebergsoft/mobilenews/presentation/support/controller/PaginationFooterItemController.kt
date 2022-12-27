package uz.icebergsoft.mobilenews.presentation.support.controller

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.ViewHolderPaginationFooterBinding

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
                btnAction.setOnClickListener { onLoadMoreClicked.invoke() }
                progressBar.visibility = GONE
                tvMessage.visibility = GONE
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
                        progressBar.visibility = VISIBLE
                        tvMessage.setText(R.string.state_loading)
                        tvMessage.visibility = VISIBLE
                        btnAction.visibility = GONE
                    }
                    PaginationState.COMPLETE -> {
                        progressBar.visibility = GONE
                        tvMessage.visibility = GONE
                        btnAction.visibility = GONE
                    }
                    PaginationState.ERROR -> {
                        progressBar.visibility = GONE
                        tvMessage.setText(R.string.state_error_title)
                        tvMessage.visibility = VISIBLE
                        btnAction.visibility = VISIBLE
                    }
                    else -> throw IllegalArgumentException("unsupported state: $state")
                }
            }
        }
    }
}