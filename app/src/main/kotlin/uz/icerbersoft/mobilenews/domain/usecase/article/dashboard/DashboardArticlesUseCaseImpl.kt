package uz.icerbersoft.mobilenews.domain.usecase.article.dashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class DashboardArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : DashboardArticlesUseCase {

    override fun getBreakingArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getBreakingNewsArticles()
            .flowOn(Dispatchers.IO)
    }

    override fun getTopArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getTopArticles()
            .flowOn(Dispatchers.IO)
    }

    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}