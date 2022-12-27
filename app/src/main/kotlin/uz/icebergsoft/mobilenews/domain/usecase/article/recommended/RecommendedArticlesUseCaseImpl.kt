package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.surfstudio.android.datalistlimitoffset.domain.datalist.DataList
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.data.utils.mapToDataList
import uz.icebergsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class RecommendedArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : RecommendedArticlesUseCase {

    val limit = 10
    var offset: Int = 0

    override fun getRecommendedArticles(): Flow<DataList<Article>> {
        return articleRepository.getRecommendedArticles(limit, offset)
            .map { it.mapToDataList() }
            .flowOn(Dispatchers.IO)
    }

    override fun increasePage() {
        offset += limit
    }


    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}