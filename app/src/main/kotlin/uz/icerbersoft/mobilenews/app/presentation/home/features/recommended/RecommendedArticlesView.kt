package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended

import moxy.MvpView
import uz.icerbersoft.mobilenews.app.usecase.article.detail.model.ArticleWrapper

interface RecommendedArticlesView : MvpView {

    fun onSuccessArticles(articles: List<ArticleWrapper>)
}