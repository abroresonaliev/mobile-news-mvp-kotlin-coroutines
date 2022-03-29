package uz.icerbersoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface RecommendedArticlesUseCase {

    fun getRecommendedArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(article: Article): Flow<Unit>
}