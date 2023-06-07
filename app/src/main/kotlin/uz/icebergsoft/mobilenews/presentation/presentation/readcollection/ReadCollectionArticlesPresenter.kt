package uz.icebergsoft.mobilenews.presentation.presentation.readcollection

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState.*
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.readcollection.router.ReadCollectionArticlesRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import javax.inject.Inject

internal class ReadCollectionArticlesPresenter @Inject constructor(
    private val useCase: RecommendedArticlesUseCase,
    private val router: ReadCollectionArticlesRouter,
) : MvpPresenter<ReadCollectionArticlesView>() {

    private val allItems: DataList<Article> = DataList.empty()
    private var state = COMPLETE

    override fun onFirstViewAttach() =
        getRecommendedArticles()

    fun getRecommendedArticles() {
        useCase.getRecommendedArticles()
            .onStart {
                if (state != READY)
                    viewState.onSuccessArticles(LoadingState, COMPLETE)
                else viewState.onSuccessArticles(SuccessState(allItems), READY)
            }
            .onEach { newDataList ->
                if (newDataList.isNotEmpty())
                    allItems.merge(newDataList)

                state = if (newDataList.isEmpty() || !allItems.canGetMore()) COMPLETE else READY

                if (allItems.isNotEmpty())
                    viewState.onSuccessArticles(SuccessState(allItems), state)
                else viewState.onSuccessArticles(EmptyState, state)
            }
            .catch {
                if (state == READY)
                    viewState.onSuccessArticles(SuccessState(allItems), ERROR)
                else viewState.onSuccessArticles(ErrorState(it.message), COMPLETE)
            }
            .launchIn(presenterScope)
    }

    fun increasePage() {
        useCase.increasePage()
        getRecommendedArticles()
    }

    fun updateBookmark(article: Article) {
        useCase.updateBookmark(article)
            .launchIn(presenterScope)
    }

    fun openArticleDetail(article: Article) {
        router.openArticleDetailScreen(article.articleId)
    }

    fun back() = router.back()
}