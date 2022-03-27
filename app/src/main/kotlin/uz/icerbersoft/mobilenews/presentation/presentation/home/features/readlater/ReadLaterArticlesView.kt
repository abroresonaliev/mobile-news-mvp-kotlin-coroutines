package uz.icerbersoft.mobilenews.presentation.presentation.home.features.readlater

import moxy.MvpView
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface ReadLaterArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>)
}