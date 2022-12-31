package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.data.utils.mapToDataList
import uz.icebergsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class RecommendedArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : RecommendedArticlesUseCase {

    var page: Int = 1

    override fun getRecommendedArticles(): Flow<DataList<Article>> {
        return articleRepository.getRecommendedArticles(page)
            .map { it.mapToDataList() }
            .flowOn(Dispatchers.IO)
    }

    override fun increasePage() {
        ++page
    }


    override fun updateBookmark(article: Article): Flow<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}