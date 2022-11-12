package uz.icerbersoft.mobilenews.presentation.presentation.setttings

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.icerbersoft.mobilenews.domain.data.entity.settings.DayNightMode
import uz.icerbersoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icerbersoft.mobilenews.domain.usecase.daynight.DayNightModeUseCase
import uz.icerbersoft.mobilenews.presentation.presentation.setttings.router.SettingsRouter
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icerbersoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
    private val useCase: DayNightModeUseCase,
    private val router: SettingsRouter
) : MvpPresenter<SettingsView>() {

    private val dayNightModeWrappers: MutableList<DayNightModeWrapper> = mutableListOf()

    override fun onFirstViewAttach() =
        getArticleDetail()

    private fun getArticleDetail() {
        useCase.getDayNightModWrappers()
            .onStart { viewState.onDefinedDayNightModeWrappers(LoadingState) }
            .onEach {
                dayNightModeWrappers.clear()
                dayNightModeWrappers.addAll(it)

                if (it.isNotEmpty()) {
                    viewState.onDefinedDayNightModeWrappers(SuccessState(it))
                } else {
                    viewState.onDefinedDayNightModeWrappers(EmptyState)
                }
            }
            .catch { viewState.onDefinedDayNightModeWrappers(ErrorState(it.localizedMessage)) }
            .launchIn(presenterScope)
    }

    fun saveDayNightMode(dayNightModeWrapper: DayNightModeWrapper) {
        useCase.setDayNightMode(dayNightModeWrapper.dayNightMode.convertToAppDelegateModeNight())

        dayNightModeWrappers.forEach {
            it.isSelected = it.dayNightMode == dayNightModeWrapper.dayNightMode
        }
        viewState.onDefinedDayNightModeWrappers(SuccessState(dayNightModeWrappers))
    }

    fun back() = router.back()
}