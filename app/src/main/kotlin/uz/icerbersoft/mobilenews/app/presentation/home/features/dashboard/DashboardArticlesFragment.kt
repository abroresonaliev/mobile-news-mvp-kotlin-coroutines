package uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard

import android.os.Bundle
import android.view.View
import dagger.Lazy
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.controller.BreakingArticleItemController
import uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.controller.TopArticleItemController
import uz.icerbersoft.mobilenews.app.presentation.home.features.dashboard.di.DashboardArticlesDaggerComponent
import uz.icerbersoft.mobilenews.app.support.controller.StateEmptyItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateErrorItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateLoadingItemController
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent.*
import uz.icerbersoft.mobilenews.app.utils.addCallback
import uz.icerbersoft.mobilenews.app.utils.onBackPressedDispatcher
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.data.utils.date.toFormattedDate
import uz.icerbersoft.mobilenews.databinding.FragmentDashboardArticlesBinding
import java.util.*
import javax.inject.Inject

internal class DashboardArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_dashboard_articles),
    IHasComponent<DashboardArticlesDaggerComponent>, DashboardArticlesView {

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

    override fun getComponent(): DashboardArticlesDaggerComponent =
        DashboardArticlesDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardArticlesBinding.bind(view)
        with(binding) {
            todayDateTv.text = Date().toFormattedDate("EEEE, dd MMMM")
            breakingArticleRv.adapter = breakingArticlesAdapter
            breakingArticleRv.itemAnimator = null
            topArticleRv.adapter = topArticlesAdapter
            topArticleRv.itemAnimator = null
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