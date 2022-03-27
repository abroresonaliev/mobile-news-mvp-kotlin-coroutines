package uz.icerbersoft.mobilenews.presentation.presentation.home.features.dashboard

import moxy.MvpView
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface DashboardArticlesView : MvpView {

    fun onDefinedBreakingArticleEvents(event: LoadingListEvent<Article>)

    fun onDefinedTopArticleWrappers(event: LoadingListEvent<Article>)
}