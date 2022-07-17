package uz.icerbersoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import uz.icerbersoft.mobilenews.domain.utils.mapToDataList
import javax.inject.Inject

class RecommendedArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : RecommendedArticlesUseCase {

    private var page: Int = 1

    override fun getRecommendedArticles(): Flow<DataList<Article>> {
        return articleRepository.getRecommendedArticles(page)
            .map { it.mapToDataList() }
            .flowOn(Dispatchers.IO)
    }

    override fun loadNextPage() {
        ++page
    }

    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}