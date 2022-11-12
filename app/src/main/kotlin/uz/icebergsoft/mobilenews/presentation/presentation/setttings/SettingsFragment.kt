package uz.icebergsoft.mobilenews.presentation.presentation.setttings

import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.FragmentSettingsBinding
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.presentation.setttings.controller.DayNightModeItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.utils.addCallback
import uz.icebergsoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import uz.icebergsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class SettingsFragment : MvpAppCompatFragment(R.layout.fragment_settings), SettingsView {

    @Inject
    lateinit var lazyPresenter: Lazy<SettingsPresenter>
    private val presenter by moxyPresenter {
        lazyPresenter.get()
    }

    private lateinit var binding: FragmentSettingsBinding

    private val easyAdapter = EasyAdapter()
    private val dayNightModeController = DayNightModeItemController {
        presenter.saveDayNightMode(it)
        (requireActivity() as GlobalActivity).updateNightMode(it.dayNightMode.convertToAppDelegateModeNight())
    }
    private val stateLoadingController = StateLoadingItemController(isFullScreen = true)
    private val stateEmptyController = StateEmptyItemController(isFullScreen = true)
    private val stateErrorController = StateErrorItemController(isFullScreen = true) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        with(binding) {
            backIv.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            settingsRv.adapter = easyAdapter
            settingsRv.itemAnimator = null
        }
    }

    override fun onDefinedDayNightModeWrappers(event: LoadingListEvent<DayNightModeWrapper>) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingState -> itemList.add(stateLoadingController)
            is SuccessState -> itemList.addAll(event.data, dayNightModeController)
            is EmptyState -> itemList.add(stateEmptyController)
            is ErrorState -> itemList.add(stateErrorController)
        }
        easyAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() =
            SettingsFragment()
    }
}