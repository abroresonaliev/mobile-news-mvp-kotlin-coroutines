package uz.icebergsoft.mobilenews.presentation.presentation.setttings.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.ViewHolderDayNightModeSelectionBinding
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightMode.*
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper

class DayNightModeItemController(
    private val onDayNightModeClick: (DayNightModeWrapper) -> Unit,
) :
    BindableItemController<DayNightModeWrapper, DayNightModeItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: DayNightModeWrapper): Any = "${ID_TAG}$data"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<DayNightModeWrapper>(
            parent,
            R.layout.view_holder_day_night_mode_selection
        ) {
        private lateinit var dayNightModeWrapper: DayNightModeWrapper

        private val binding = ViewHolderDayNightModeSelectionBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onDayNightModeClick.invoke(dayNightModeWrapper)
            }
        }

        override fun bind(data: DayNightModeWrapper) {
            dayNightModeWrapper = data
            with(binding) {
                tvDayNightModeName.text = when (data.dayNightMode) {
                    ONLY_LIGHT_MODE -> "Light mode"
                    ONLY_NIGHT_MODE -> "Night mode"
                    FOLLOW_SYSTEM_NIGHT_MODE -> "Follow system"
                }

                if (data.isSelected)
                    tvDayNightModeName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_selected, 0)
                else
                    tvDayNightModeName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
        }
    }

    private companion object {
        const val ID_TAG = "DayNightModeItemController"
    }
}