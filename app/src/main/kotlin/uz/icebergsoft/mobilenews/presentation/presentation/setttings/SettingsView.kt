package uz.icebergsoft.mobilenews.presentation.presentation.setttings

import moxy.MvpView
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent

interface SettingsView : MvpView {

    fun onDefinedDayNightModeWrappers(event: LoadingListEvent<DayNightModeWrapper>)
}