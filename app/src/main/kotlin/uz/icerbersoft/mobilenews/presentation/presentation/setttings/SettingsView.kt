package uz.icerbersoft.mobilenews.presentation.presentation.setttings

import moxy.MvpView
import uz.icerbersoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent

interface SettingsView : MvpView {

    fun onDefinedDayNightModeWrappers(wrappers: LoadingListEvent<DayNightModeWrapper>)
}