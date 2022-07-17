package uz.icerbersoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface RecommendedArticlesUseCase {

    fun getRecommendedArticles(): Flow<DataList<Article>>

    fun loadNextPage()

    fun updateBookmark(article: Article): Flow<Unit>
}