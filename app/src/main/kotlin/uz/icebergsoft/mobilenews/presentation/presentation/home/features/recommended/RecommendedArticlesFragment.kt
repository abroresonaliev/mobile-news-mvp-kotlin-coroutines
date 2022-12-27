package uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended

import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.pagination.EasyPaginationAdapter
import ru.surfstudio.android.easyadapter.pagination.PaginationState
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.FragmentRecommendedArticlesBinding
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended.controller.RecommendedArticleItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.PaginationFooterItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.utils.addCallback
import uz.icebergsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class RecommendedArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_recommended_articles), RecommendedArticlesView {

    @Inject
    lateinit var lazyPresenter: Lazy<RecommendedArticlesPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private lateinit var binding: FragmentRecommendedArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    private val paginationFooterController = PaginationFooterItemController {
        presenter.getRecommendedArticles()
    }
    private val easyPaginationAdapter = EasyPaginationAdapter(paginationFooterController) {
        presenter.increasePage()
    }
    private val articleController = RecommendedArticleItemController(
        itemClickListener = { presenter.openArticleDetail(it) },
        bookmarkListener = { presenter.updateBookmark(it) }
    )
    private val stateLoadingController = StateLoadingItemController(true)
    private val stateEmptyController = StateEmptyItemController(true)
    private val stateErrorController =
        StateErrorItemController(true) { presenter.getRecommendedArticles() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecommendedArticlesBinding.bind(view)
        with(binding) {
            easyPaginationAdapter.isFirstInvisibleItemEnabled = true
            recyclerView.adapter = easyPaginationAdapter
            recyclerView.itemAnimator = null
        }
    }

    override fun onSuccessArticles(event: LoadingListEvent<Article>, state: PaginationState) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingState -> itemList.add(stateLoadingController)
            is SuccessState -> itemList.addAll(event.data, articleController)
            is EmptyState -> itemList.add(stateEmptyController)
            is ErrorState -> itemList.add(stateErrorController)
        }
        easyPaginationAdapter.setItems(itemList, state)
    }

    companion object {

        fun newInstance() = RecommendedArticlesFragment()
    }
}