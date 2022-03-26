package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard

import moxy.MvpView
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.data.model.article.Article

interface DashboardArticlesView : MvpView {

    fun onDefinedBreakingArticleEvents(event: LoadingListEvent<Article>)

    fun onDefinedTopArticleWrappers(event: LoadingListEvent<Article>)
}