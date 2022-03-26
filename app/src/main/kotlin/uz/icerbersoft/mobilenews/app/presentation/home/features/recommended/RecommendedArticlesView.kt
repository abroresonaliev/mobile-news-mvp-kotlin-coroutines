package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended

import moxy.MvpView
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.data.model.article.Article

interface RecommendedArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>)
}