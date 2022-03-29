package uz.icerbersoft.mobilenews.presentation.presentation.home.di

import dagger.Subcomponent
import uz.icerbersoft.mobilenews.presentation.presentation.home.HomeFragment

@HomeDaggerScope
@Subcomponent(modules = [HomeDaggerModule::class])
internal interface HomeDaggerComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeDaggerComponent
    }
}