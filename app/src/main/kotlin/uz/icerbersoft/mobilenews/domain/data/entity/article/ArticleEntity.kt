package uz.icerbersoft.mobilenews.domain.data.entity.article

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
internal data class ArticleEntity(

    @PrimaryKey
    @ColumnInfo(name = "article_article_id")
    val articleId: String,

    @ColumnInfo(name = "article_title")
    val title: String,

    @ColumnInfo(name = "article_desc")
    val description: String,

    @ColumnInfo(name = "article_content")
    val content: String,

    @ColumnInfo(name = "article_author")
    val author: String,

    @ColumnInfo(name = "article_url")
    val url: String,

    @ColumnInfo(name = "article_image_url")
    val imageUrl: String,

    @ColumnInfo(name = "article_source")
    val source: String,

    @ColumnInfo(name = "article_source_id")
    val sourceId: String?,

    @ColumnInfo(name = "article_published_at")
    val publishedAt: String,

    @ColumnInfo(name = "article_is_bookmarked")
    val isBookmarked: Boolean
)