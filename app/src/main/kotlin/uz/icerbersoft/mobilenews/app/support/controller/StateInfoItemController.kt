package uz.icerbersoft.mobilenews.app.support.controller

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder
import uz.icerbersoft.mobilenews.app.R

class StateInfoItemController(
    private val isFullScreen: Boolean = false,
    private val message: String? = null,
    private val iconResId: Int? = null
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.view_holder_state_info).apply {
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
            if (message != null) {
                itemView.findViewById<TextView>(R.id.info_tv).text = message
            }
            if (iconResId != null && iconResId > 0) {
                itemView.findViewById<ImageView>(R.id.info_iv).setImageResource(iconResId)
            }
        }
}