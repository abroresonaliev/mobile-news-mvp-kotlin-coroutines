package uz.icebergsoft.mobilenews.domain.usecase.bookmark

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository

class BookmarkUseCaseImpl(
    private val articleRepository: ArticleRepository
) : BookmarkUseCase {

    override fun updateBookmark(article: Article): Flow<Unit> {
        return articleRepository.updateBookmark(article.articleId, !article.isBookmarked)
            .flowOn(Dispatchers.IO)
    }
}