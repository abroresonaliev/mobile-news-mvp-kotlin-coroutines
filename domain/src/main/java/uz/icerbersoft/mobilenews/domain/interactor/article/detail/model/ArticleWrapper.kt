package uz.icerbersoft.mobilenews.domain.interactor.article.detail.model

import uz.icerbersoft.mobilenews.data.model.article.Article

sealed class ArticleWrapper {

    object LoadingItem : ArticleWrapper()

    data class ArticleItem(val article: Article) : ArticleWrapper()

    object EmptyItem : ArticleWrapper()

    object ErrorItem : ArticleWrapper()
}
