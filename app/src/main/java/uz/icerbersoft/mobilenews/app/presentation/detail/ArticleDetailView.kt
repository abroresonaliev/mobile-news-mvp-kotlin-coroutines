package uz.icerbersoft.mobilenews.app.presentation.detail

import moxy.MvpView
import uz.icerbersoft.mobilenews.data.model.article.Article

interface ArticleDetailView : MvpView {

    fun onSuccessArticleDetail(article: Article)
}