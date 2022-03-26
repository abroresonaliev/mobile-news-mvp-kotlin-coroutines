package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater

import moxy.MvpView
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.data.model.article.Article

interface ReadLaterArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>)
}