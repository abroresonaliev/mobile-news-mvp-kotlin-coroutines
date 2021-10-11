package uz.icerbersoft.mobilenews.app.global.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.icerbersoft.mobilenews.app.presentation.detail.ArticleDetailFragment
import uz.icerbersoft.mobilenews.app.presentation.home.HomeFragment
import uz.icerbersoft.mobilenews.app.support.cicerone.base.CiceroneRouter

class GlobalRouter : CiceroneRouter() {

    fun openHomeScreen() {
        newRootScreen(Screens.Home)
    }

    fun openArticleDetailScreen(articleId: String) {
        navigateTo(Screens.ArticleDetail(articleId))
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
    }
}