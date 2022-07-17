package uz.icerbersoft.mobilenews.presentation.presentation.home.features.recommended

import moxy.MvpView
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import uz.icerbersoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface RecommendedArticlesView : MvpView {

    fun onDefinedArticles(event: LoadingListEvent<Article>, state: PaginationState)
}