package uz.icerbersoft.mobilenews.app.presentation.home.features.recommended

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
import uz.icerbersoft.mobilenews.app.databinding.FragmentRecommendedArticlesBinding
import uz.icerbersoft.mobilenews.app.presentation.home.features.recommended.controller.RecommendedArticleItemController
import uz.icerbersoft.mobilenews.app.presentation.home.features.recommended.di.RecommendedArticlesDaggerComponent
import uz.icerbersoft.mobilenews.app.support.controller.StateEmptyItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateErrorItemController
import uz.icerbersoft.mobilenews.app.support.controller.StateLoadingItemController
import uz.icerbersoft.mobilenews.app.utils.addCallback
import uz.icerbersoft.mobilenews.app.utils.onBackPressedDispatcher
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper
import javax.inject.Inject

internal class RecommendedArticlesFragment :
    MvpAppCompatFragment(R.layout.fragment_recommended_articles),
    IHasComponent<RecommendedArticlesDaggerComponent>, RecommendedArticlesView {

    @Inject
    lateinit var lazyPresenter: Lazy<RecommendedArticlesPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private lateinit var binding: FragmentRecommendedArticlesBinding

    override fun getComponent(): RecommendedArticlesDaggerComponent =
        RecommendedArticlesDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    private val easyAdapter = EasyAdapter()
    private val articleController = RecommendedArticleItemController(
        itemClickListener = { presenter.openArticleDetail(it) },
        bookmarkListener = { presenter.updateBookmark(it) }
    )
    private val stateLoadingController = StateLoadingItemController(true)
    private val stateEmptyItemController = StateEmptyItemController(true)
    private val stateErrorController =
        StateErrorItemController(true) { presenter.getRecommendedArticles() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecommendedArticlesBinding.bind(view)
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

        fun newInstance() = RecommendedArticlesFragment()
    }
}