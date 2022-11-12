package uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard

import moxy.MvpView
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface DashboardArticlesView : MvpView {

    fun onDefinedBreakingArticleEvents(event: LoadingListEvent<Article>)

    fun onDefinedTopArticleWrappers(event: LoadingListEvent<Article>)
}