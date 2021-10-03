package uz.icerbersoft.mobilenews.app.presentation.home.features.readlater.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.icerbersoft.mobilenews.app.R
import uz.icerbersoft.mobilenews.app.databinding.ViewHolderReadLaterArticleBinding
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.domain.interactor.article.detail.model.ArticleWrapper.ArticleItem

internal class ReadLaterArticleItemController(
    private val itemClickListener: (product: Article) -> Unit,
    private val bookmarkListener: (Article) -> Unit
) : BindableItemController<ArticleItem, ReadLaterArticleItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: ArticleItem) = "$ID_TAG${data.article.articleId}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<ArticleItem>(parent, R.layout.view_holder_read_later_article) {

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

        override fun bind(data: ArticleItem) {
            article = data.article
            with(binding) {
                titleTextView.text = data.article.title
                sourceTextView.text = data.article.source.name
                publishedAtTextView.text = data.article.publishedAt
                imageSimpleImageView.setImageURI(data.article.imageUrl)
//                bookmarkImageView.apply {
//                    if (data.article.isBookmarked) setImageResource(R.drawable.drawable_bookmark)
//                    else setImageResource(R.drawable.drawable_bookmark_border)
//                }
            }
        }
    }

    private companion object {
        const val ID_TAG = "ArticleItemController"
    }
}