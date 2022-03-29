package uz.icerbersoft.mobilenews.domain.usecase.bookmark

import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface BookmarkUseCase {

    fun updateBookmark(article: Article): Flow<Unit>
}