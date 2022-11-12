package uz.icebergsoft.mobilenews.presentation.global.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.icebergsoft.mobilenews.presentation.presentation.detail.ArticleDetailFragment
import uz.icebergsoft.mobilenews.presentation.presentation.home.HomeFragment
import uz.icebergsoft.mobilenews.presentation.presentation.setttings.SettingsFragment
import uz.icebergsoft.mobilenews.presentation.support.cicerone.router.BaseCiceroneRouter

class GlobalRouter : BaseCiceroneRouter() {

    fun openHomeScreen() {
        newRootScreen(Screens.Home)
    }

    fun openArticleDetailScreen(articleId: String) {
        navigateTo(Screens.ArticleDetail(articleId))
    }

    fun openSettingsScreen() {
        navigateTo(Screens.Settings)
    }

    private object Screens {

        object Home : SupportAppScreen() {
            override fun getFragment(): Fragment =
                HomeFragment.newInstance()
        }

        class ArticleDetail(val articleId: String) : SupportAppScreen() {
            override fun getFragment(): Fragment =
                ArticleDetailFragment.newInstance(articleId)
        }

        object Settings : SupportAppScreen() {
            override fun getFragment(): Fragment =
                SettingsFragment.newInstance()
        }
    }
}