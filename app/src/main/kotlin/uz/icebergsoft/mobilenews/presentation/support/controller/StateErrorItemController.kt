package uz.icebergsoft.mobilenews.presentation.support.controller

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder
import uz.icebergsoft.mobilenews.R

class StateErrorItemController(
    private val isFullScreen: Boolean = false,
    private val onActionClickLister: () -> Unit
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.view_holder_state_error).apply {
            itemView.findViewById<Button>(R.id.btn_action).setOnClickListener {
                onActionClickLister.invoke()
            }
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
        }
}