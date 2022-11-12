package uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended

import moxy.MvpView
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface RecommendedArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>)
}