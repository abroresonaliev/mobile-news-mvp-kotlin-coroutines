package uz.icerbersoft.mobilenews.domain.usecase.article.readlater

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface ReadLaterArticlesUseCase {

    fun getReadLaterArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(article: Article): Flow<Unit>
}