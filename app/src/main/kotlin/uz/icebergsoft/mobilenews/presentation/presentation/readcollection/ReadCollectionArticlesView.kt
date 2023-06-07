package uz.icebergsoft.mobilenews.presentation.presentation.readcollection

import moxy.MvpView
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent

interface ReadCollectionArticlesView : MvpView {

    fun onSuccessArticles(event: LoadingListEvent<Article>, state: PaginationState)
}