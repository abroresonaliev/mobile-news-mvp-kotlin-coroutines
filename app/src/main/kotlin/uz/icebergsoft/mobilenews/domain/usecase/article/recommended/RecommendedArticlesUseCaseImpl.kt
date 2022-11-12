package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class RecommendedArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : RecommendedArticlesUseCase {

    override fun getRecommendedArticles(): Flow<ArticleListWrapper> {
        return articleRepository.getRecommendedArticles()
            .flowOn(Dispatchers.IO)
    }

    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}