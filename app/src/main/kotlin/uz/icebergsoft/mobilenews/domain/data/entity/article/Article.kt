package uz.icebergsoft.mobilenews.domain.data.entity.article

import uz.icebergsoft.mobilenews.domain.data.entity.source.Source

data class Article(
    val articleId: String,
    val author: String,
    val content: String,
    val description: String,
    val category: String,
    val country: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val imageUrl: String,
    val isBookmarked: Boolean,
    val isBreaking: Boolean,
    val isTop: Boolean,
    val isRecommended: Boolean,
)