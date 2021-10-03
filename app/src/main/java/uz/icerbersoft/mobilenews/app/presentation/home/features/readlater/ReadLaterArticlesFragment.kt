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
import uz.icerbersoft.mobilenews.app.R
import uz.icerbersoft.mobilenews.app.databinding.FragmentReadLaterArticlesBinding
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.controller.ReadLaterArticleItemController
import uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.di.ReadLaterArticlesDaggerComponent
import uz.icerbersoft.mobilenews.app.support.cicerone.utils.withArguments
import uz.icerbersoft.mobilenews.app.support.controller.StateEmptyItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateErrorItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateLoadingItemController
import uz.icerbersoft.mobilenews.app.utils.addCallback
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper
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
        requireActivity().onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReadLaterArticlesBinding.bind(view)
        with(binding) {
            recyclerView.adapter = easyAdapter
            recyclerView.itemAnimator = null
        }
    }

    override fun onSuccessArticles(articles: List<ArticleWrapper>) {
        val itemList = ItemList.create()
        for (item in articles) {
            when (item) {
                is ArticleWrapper.ArticleItem -> itemList.add(item, articleController)
                is ArticleWrapper.EmptyItem -> itemList.add(stateEmptyItemController)
                is ArticleWrapper.ErrorItem -> itemList.add(stateErrorController)
                is ArticleWrapper.LoadingItem -> itemList.add(stateLoadingController)
            }
        }
        easyAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() =
            ReadLaterArticlesFragment().withArguments()
    }
}