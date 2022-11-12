package uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater

import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.FragmentReadLaterArticlesBinding
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater.controller.ReadLaterArticleItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateEmptyItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateErrorItemController
import uz.icebergsoft.mobilenews.presentation.support.controller.StateLoadingItemController
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.utils.addCallback
import uz.icebergsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class ReadLaterArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_read_later_articles), ReadLaterArticlesView {

    @Inject
    lateinit var lazyPresenter: Lazy<ReadLaterArticlesPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private lateinit var binding: FragmentReadLaterArticlesBinding

    private val easyAdapter = EasyAdapter()
    private val articleController = ReadLaterArticleItemController(
        itemClickListener = { presenter.openArticleDetailScreen(it) },
        bookmarkListener = { presenter.updateBookmark(it) }
    )
    private val stateLoadingController = StateLoadingItemController(true)
    private val stateEmptyItemController = StateEmptyItemController(true)
    private val stateErrorController =
        StateErrorItemController(true) { presenter.getReadLaterArticles() }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReadLaterArticlesBinding.bind(view)
        with(binding) {
            recyclerView.adapter = easyAdapter
            recyclerView.itemAnimator = null
        }
    }

    override fun onSuccessArticles(event: LoadingListEvent<Article>) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingListEvent.LoadingState -> itemList.add(stateLoadingController)
            is LoadingListEvent.SuccessState -> itemList.addAll(event.data, articleController)
            is LoadingListEvent.EmptyState -> itemList.add(stateEmptyItemController)
            is LoadingListEvent.ErrorState -> itemList.add(stateErrorController)
        }
        easyAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() = ReadLaterArticlesFragment()
    }
}