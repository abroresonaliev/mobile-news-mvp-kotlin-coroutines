package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard

import moxy.MvpView
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper

interface DashboardArticlesView : MvpView {

    fun onDefinedBreakingArticleWrappers(articles: List<ArticleWrapper>)

    fun onDefinedTopArticleWrappers(articles: List<ArticleWrapper>)
}