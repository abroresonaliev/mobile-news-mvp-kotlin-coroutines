package uz.icebergsoft.mobilenews.domain.usecase.article.detail

import kotlinx.coroutines.flow.Flow
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface ArticleDetailUseCase {

    fun getArticle(articleId: String): Flow<Article>

    fun updateBookmark(article: Article): Flow<Unit>
}