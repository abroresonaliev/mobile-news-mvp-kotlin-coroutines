package uz.icerbersoft.mobilenews.data.mapper

import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleEntity
import uz.icerbersoft.mobilenews.domain.data.entity.source.Source
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleResponse
import uz.icerbersoft.mobilenews.data.utils.date.toFormattedDate

internal fun ArticleEntity.entityToArticle(): Article =
    Article(
        articleId = articleId,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = Source(sourceId, source),
        title = title,
        url = url,
        imageUrl = imageUrl,
        isBookmarked = isBookmarked
    )

internal fun Article.toEntity(): ArticleEntity =
    ArticleEntity(
        articleId = articleId,
        title = title,
        description = description,
        content = content,
        author = author,
        url = url,
        imageUrl = imageUrl,
        source = source.name,
        sourceId = source.id,
        publishedAt = publishedAt,
        isBookmarked = false
    )

internal fun ArticleResponse.responseToEntity(): ArticleEntity =
    ArticleEntity(
        articleId = url.hashCode().toString(),
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt?.toFormattedDate() ?: "",
        source = source.name,
        sourceId = source.id,
        title = title,
        url = url,
        imageUrl = imageUrl ?: "",
        isBookmarked = false
    )