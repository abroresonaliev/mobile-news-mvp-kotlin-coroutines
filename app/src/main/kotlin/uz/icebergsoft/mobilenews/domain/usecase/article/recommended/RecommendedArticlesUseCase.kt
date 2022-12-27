package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.android.datalistlimitoffset.domain.datalist.DataList
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface RecommendedArticlesUseCase {

    fun getRecommendedArticles(): Flow<DataList<Article>>

    fun increasePage()

    fun updateBookmark(article: Article): Flow<Unit>
}