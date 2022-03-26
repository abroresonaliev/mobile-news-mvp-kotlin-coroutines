package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater

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
import uz.icerbersoft.mobilenews.databinding.FragmentReadLaterArticlesBinding
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.controller.ReadLaterArticleItemController
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.di.ReadLaterArticlesDaggerComponent
import uz.icerbersoft.mobilenews.app.support.controller.StateEmptyItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateErrorItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateLoadingItemController
import uz.icerbersoft.mobilenews.app.support.event.LoadingListEvent
import uz.icerbersoft.mobilenews.app.utils.addCallback
import uz.icerbersoft.mobilenews.app.utils.onBackPressedDispatcher
import uz.icerbersoft.mobilenews.data.model.article.Article
import javax.inject.Inject

internal class ReadLaterArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_read_later_articles),
    IHasComponent<ReadLaterArticlesDaggerComponent>, ReadLaterArticlesView {

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


    override fun getComponent(): ReadLaterArticlesDaggerComponent =
        ReadLaterArticlesDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
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