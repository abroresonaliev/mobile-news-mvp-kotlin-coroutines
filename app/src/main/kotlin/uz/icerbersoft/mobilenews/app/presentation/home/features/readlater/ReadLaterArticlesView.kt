package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater

import moxy.MvpView
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper

interface ReadLaterArticlesView : MvpView {

    fun onSuccessArticles(articles: List<ArticleWrapper>)
}