package uz.icerbersoft.mobilenews.app.global.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter

@Module(
    includes = [
        GlobalDaggerModuleNavigation.GlobalRouterProviders::class,
        GlobalDaggerModuleNavigation.HomerRouterProviders::class
    ]
)
object GlobalDaggerModuleNavigation {

    @Module
    object GlobalRouterProviders {

        private val globalCicerone: Cicerone<GlobalRouter> = Cicerone.create(GlobalRouter())

        @JvmStatic
        @Provides
        @GlobalScope
        fun globalRouter(): GlobalRouter = globalCicerone.router

        @JvmStatic
        @Provides
        fun globalCicerone(): Cicerone<GlobalRouter> = globalCicerone
    }

    @Module
    object HomerRouterProviders {

        @JvmStatic
        @Provides
        @GlobalScope
        fun homerRouter(): HomeRouter = HomeRouter()
    }
}