package uz.icebergsoft.mobilenews.presentation.presentation.detail

import moxy.MvpView
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface ArticleDetailView : MvpView {

    fun onSuccessArticleDetail(article: Article)
}