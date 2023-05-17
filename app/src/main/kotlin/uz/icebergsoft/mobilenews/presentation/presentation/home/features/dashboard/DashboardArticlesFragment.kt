package uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard

import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.data.utils.date.toFormattedDate
import uz.icebergsoft.mobilenews.databinding.FragmentDashboardArticlesBinding
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard.controller.BreakingArticleItemController
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.dashboard.controller.TopArticleItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.utils.addCallback
import uz.icebergsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import java.util.*
import javax.inject.Inject

internal class DashboardArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_dashboard_articles), DashboardArticlesView {

    @Inject
    lateinit var lazyPresenter: Lazy<DashboardArticlesPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private lateinit var binding: FragmentDashboardArticlesBinding

    private val breakingArticlesAdapter = EasyAdapter()
    private val breakingArticleController = BreakingArticleItemController(
        itemClickListener = { presenter.openArticleDetailScreen(it) },
        bookmarkListener = { presenter.updateBookmark(it) }
    )
    private val breakingLoadingController = StateLoadingItemController(true)
    private val breakingEmptyController = StateEmptyItemController(true)
    private val breakingErrorController =
        StateErrorItemController(true) { presenter.getBreakingArticles() }

    private val topArticlesAdapter = EasyAdapter()
    private val topArticleController = TopArticleItemController(
        itemClickListener = { presenter.openArticleDetailScreen(it) },
        bookmarkListener = { presenter.updateBookmark(it) }
    )
    private val topLoadingController = StateLoadingItemController(true)
    private val topEmptyController = StateEmptyItemController(true)
    private val topErrorController =
        StateErrorItemController(true) { presenter.getTopArticles() }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardArticlesBinding.bind(view)
        with(binding) {
            tvTodayDate.text = Date().toFormattedDate("EEEE, dd MMMM")

            rvBreakingArticles.adapter = breakingArticlesAdapter
            rvBreakingArticles.itemAnimator = null

            rvTopArticles.adapter = topArticlesAdapter
            rvTopArticles.itemAnimator = null

            ivSettings.setOnClickListener { presenter.openSettingsScreen() }
        }
    }

    override fun onDefinedBreakingArticleEvents(event: LoadingListEvent<Article>) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingState -> itemList.add(breakingLoadingController)
            is SuccessState -> itemList.addAll(event.data, breakingArticleController)
            is EmptyState -> itemList.add(breakingEmptyController)
            is ErrorState -> itemList.add(breakingErrorController)
        }
        breakingArticlesAdapter.setItems(itemList)
    }

    override fun onDefinedTopArticleWrappers(event: LoadingListEvent<Article>) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingState -> itemList.add(topLoadingController)
            is SuccessState -> itemList.addAll(event.data, topArticleController)
            is EmptyState -> itemList.add(topEmptyController)
            is ErrorState -> itemList.add(topErrorController)
        }
        topArticlesAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() = DashboardArticlesFragment()
    }
}