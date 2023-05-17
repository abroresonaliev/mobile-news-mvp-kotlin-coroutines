package uz.icebergsoft.mobilenews.presentation.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.FragmentArticleDetailBinding
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebergsoft.mobilenews.presentation.utils.addCallback
import uz.icebergsoft.mobilenews.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class ArticleDetailFragment : MvpAppCompatFragment(R.layout.fragment_article_detail),
    ArticleDetailView {

    @Inject
    lateinit var lazyPresenter: Lazy<ArticleDetailPresenter>
    private val presenter by moxyPresenter {
        lazyPresenter.get()
            .apply { setArticleId(checkNotNull(arguments?.getString(KEY_ARTICLE_ID))) }
    }

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleDetailBinding.bind(view)

        with(binding){
            backIv.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    override fun onSuccessArticleDetail(article: Article) {
        with(binding) {
            detailImageSdv.setImageURI(article.imageUrl)
            tvPublishedDate.text = article.publishedAt
            tvTitle.text = article.title
            tvSource.text = article.source.name
            contentTextView.text = article.content

            bookmarkIv.apply {
                if (article.isBookmarked) setImageResource(R.drawable.ic_bookmark)
                else setImageResource(R.drawable.ic_bookmark_border)
            }

            bookmarkIv.setOnClickListener { presenter.updateBookmark(article) }

            shareIv.setOnClickListener {
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

        fun newInstance(articleId: String) =
            ArticleDetailFragment().apply {
                arguments = Bundle().apply { putString(KEY_ARTICLE_ID, articleId) }
            }
    }
}