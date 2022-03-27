package uz.icerbersoft.mobilenews.presentation.presentation.home.features.recommended

import moxy.MvpView
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface RecommendedArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>)
}