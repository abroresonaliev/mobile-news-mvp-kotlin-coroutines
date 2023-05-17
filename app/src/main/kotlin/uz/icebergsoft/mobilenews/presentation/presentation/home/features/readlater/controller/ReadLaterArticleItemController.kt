package uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.icebergsoft.mobilenews.R
import uz.icebergsoft.mobilenews.databinding.ViewHolderArticleReadLaterBinding
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

internal class ReadLaterArticleItemController(
    private val itemClickListener: (product: Article) -> Unit,
    private val bookmarkListener: (Article) -> Unit
) : BindableItemController<Article, ReadLaterArticleItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: Article) = "$ID_TAG${data.articleId}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<Article>(parent, R.layout.view_holder_article_read_later) {

        private lateinit var article: Article
        private val binding = ViewHolderArticleReadLaterBinding.bind(itemView)

        init {
            with(binding) {
                itemParent.setOnClickListener { itemClickListener.invoke(article) }
//                ivBookmark.apply {
////                    if (article.isBookmarked) setImageResource(R.drawable.drawable_bookmark)
////                    else setImageResource(R.drawable.drawable_bookmark_border)
//                    setOnClickListener { bookmarkListener.invoke(article) }
//                }
            }
        }

        override fun bind(data: Article) {
            article = data
            with(binding) {
                tvTitle.text = data.title
                tvSource.text = data.source.name
                tvPublishedDate.text = data.publishedAt
                sdvArticle.setImageURI(data.imageUrl)
            }
        }
    }

    private companion object {
        const val ID_TAG = "ReadLaterArticleItemController"
    }
}