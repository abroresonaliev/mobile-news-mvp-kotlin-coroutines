package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.router

import uz.icerbersoft.mobilenews.app.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.app.support.cicerone.base.FeatureRouter
import javax.inject.Inject

class DashboardArticlesRouter @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : FeatureRouter()