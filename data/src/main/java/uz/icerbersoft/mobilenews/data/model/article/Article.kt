package uz.icerbersoft.mobilenews.data.model.article

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