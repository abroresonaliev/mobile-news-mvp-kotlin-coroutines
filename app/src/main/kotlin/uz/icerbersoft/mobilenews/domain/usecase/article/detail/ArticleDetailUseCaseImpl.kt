package uz.icerbersoft.mobilenews.domain.usecase.article.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class ArticleDetailUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : ArticleDetailUseCase {

    override fun getArticle(articleId: String): Flow<Article> {
        return articleRepository.getArticle(articleId)
            .flowOn(Dispatchers.IO)
    }

    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}