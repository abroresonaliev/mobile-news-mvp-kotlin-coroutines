package uz.icebergsoft.mobilenews.presentation.presentation.home.di

import dagger.Subcomponent
import uz.icebergsoft.mobilenews.presentation.presentation.home.HomeFragment

@HomeDaggerScope
@Subcomponent(modules = [HomeDaggerModule::class])
internal interface HomeDaggerComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeDaggerComponent
    }
}