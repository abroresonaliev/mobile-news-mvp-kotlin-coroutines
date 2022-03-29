package uz.icerbersoft.mobilenews.domain.usecase.article.dashboard

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface DashboardArticlesUseCase {

    fun getBreakingArticles(): Flow<ArticleListWrapper>

    fun getTopArticles(): Flow<ArticleListWrapper>

    fun updateBookmark(article: Article): Flow<Unit>
}