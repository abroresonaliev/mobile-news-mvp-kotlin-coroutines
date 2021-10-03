package uz.icerbersoft.mobilenews.app.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import dagger.Lazy
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.icerbersoft.mobilenews.app.R
import uz.icerbersoft.mobilenews.app.databinding.FragmentArticleDetailBinding
import uz.icerbersoft.mobilenews.app.presentation.detail.di.ArticleDetailDaggerComponent
import uz.icerbersoft.mobilenews.app.support.cicerone.utils.getNonNullBundleArgument
import uz.icerbersoft.mobilenews.app.support.cicerone.utils.withArguments
import uz.icerbersoft.mobilenews.app.utils.addCallback
import uz.icerbersoft.mobilenews.app.utils.onBackPressedDispatcher
import uz.icerbersoft.mobilenews.data.model.article.Article
import javax.inject.Inject

internal class ArticleDetailFragment : MvpAppCompatFragment(R.layout.fragment_article_detail),
    IHasComponent<ArticleDetailDaggerComponent>, ArticleDetailView {

    @Inject
    lateinit var lazyPresenter: Lazy<ArticleDetailPresenter>
    private val presenter by moxyPresenter {
        lazyPresenter.get().apply { setArticleId(getNonNullBundleArgument(KEY_ARTICLE_ID)) }
    }

    private lateinit var binding: FragmentArticleDetailBinding

    override fun getComponent(): ArticleDetailDaggerComponent =
        ArticleDetailDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleDetailBinding.bind(view)
    }

    override fun onSuccessArticleDetail(article: Article) {
        with(binding) {
            imageSimpleImageView.setImageURI(article.imageUrl)
            publishedAtTextView.text = article.publishedAt
            titleTextView.text = article.title
            sourceTextView.text = article.source.name
            contentTextView.text = article.content

            shareButton.setOnClickListener {
                val shareText =
                    "${article.title}\n\nMobile news - interesting news in your mobile.\n\n${article.url}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Share")
                startActivity(shareIntent)
            }
        }
    }

    companion object {

        private const val KEY_ARTICLE_ID: String = "key_article_id"

        fun newInstance(articleId: Long) =
            ArticleDetailFragment()
                .withArguments { putSerializable(KEY_ARTICLE_ID, articleId) }
    }
}