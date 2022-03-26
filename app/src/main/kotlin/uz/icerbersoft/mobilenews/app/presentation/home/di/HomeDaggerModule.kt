package uz.icerbersoft.mobilenews.app.presentation.home.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter

@Module
internal object HomeDaggerModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @JvmStatic
    @Provides
    fun homerNavigatorHolder(
        homeRouter: HomeRouter,
    ): NavigatorHolder {
        homeRouter.setRouter(cicerone.router)
        return cicerone.navigatorHolder
    }
}