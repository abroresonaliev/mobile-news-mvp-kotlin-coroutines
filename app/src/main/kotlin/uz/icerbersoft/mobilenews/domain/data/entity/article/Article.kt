package uz.icerbersoft.mobilenews.domain.data.entity.article

import uz.icerbersoft.mobilenews.domain.data.entity.source.Source

data class Article(
    val articleId: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val imageUrl: String,
    val isBookmarked: Boolean
)