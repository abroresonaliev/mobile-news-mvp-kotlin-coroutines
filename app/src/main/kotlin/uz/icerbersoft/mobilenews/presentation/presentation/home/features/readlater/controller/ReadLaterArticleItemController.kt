package uz.icerbersoft.mobilenews.presentation.presentation.home.features.readlater.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.databinding.ViewHolderReadLaterArticleBinding

internal class ReadLaterArticleItemController(
    private val itemClickListener: (product: Article) -> Unit,
    private val bookmarkListener: (Article) -> Unit
) : BindableItemController<Article, ReadLaterArticleItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: Article) = "$ID_TAG${data.articleId}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<Article>(parent, R.layout.view_holder_read_later_article) {

        private lateinit var article: Article
        private val binding = ViewHolderReadLaterArticleBinding.bind(itemView)

        init {
            with(binding) {
                itemParent.setOnClickListener { itemClickListener.invoke(article) }
//                bookmarkImageView.apply {
////                    if (article.isBookmarked) setImageResource(R.drawable.drawable_bookmark)
////                    else setImageResource(R.drawable.drawable_bookmark_border)
//                    setOnClickListener { bookmarkListener.invoke(article) }
//                }
            }
        }

        override fun bind(data: Article) {
            article = data
            with(binding) {
                titleTextView.text = data.title
                sourceTextView.text = data.source.name
                publishedAtTextView.text = data.publishedAt
                imageSimpleImageView.setImageURI(data.imageUrl)
//                bookmarkImageView.apply {
//                    if (data.isBookmarked) setImageResource(R.drawable.drawable_bookmark)
//                    else setImageResource(R.drawable.drawable_bookmark_border)
//                }
            }
        }
    }

    private companion object {
        const val ID_TAG = "ReadLaterArticleItemController"
    }
}