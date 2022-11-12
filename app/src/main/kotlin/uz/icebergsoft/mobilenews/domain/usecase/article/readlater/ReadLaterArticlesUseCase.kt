package uz.icebergsoft.mobilenews.domain.usecase.article.readlater

import kotlinx.coroutines.flow.Flow
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface ReadLaterArticlesUseCase {

    fun getReadLaterArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(article: Article): Flow<Unit>
}