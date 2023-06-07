package uz.icebergsoft.mobilenews.data.mapper

import uz.icebergsoft.mobilenews.data.model.article.ArticleEntity
import uz.icebergsoft.mobilenews.data.model.article.ArticleResponse
import uz.icebergsoft.mobilenews.data.utils.date.toFormattedDate
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.source.Source
import java.util.*

internal fun ArticleEntity.entityToArticle(): Article =
    Article(
        articleId = articleId,
        author = author,
        content = content,
        description = description,
        category = category,
        country = country,
        publishedAt = publishedAt,
        source = Source(sourceId, source),
        title = title,
        url = url,
        imageUrl = imageUrl,
        isBookmarked = isBookmarked,
        isBreaking = isBreaking,
        isTop = isTop,
        isRecommended = isRecommended
    )

internal fun ArticleResponse.mapTo(
    country: String,
    category: String,
    isBreaking: Boolean = false,
    isTop: Boolean = false,
    isRecommended: Boolean =false
): ArticleEntity =
    ArticleEntity(
        articleId = url.hashCode().toString(),
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        category = category,
        country = country,
        publishedAt = publishedAt?.toFormattedDate() ?: "",
        savedAt = Date().time,
        source = source.name,
        sourceId = source.id,
        title = title,
        url = url,
        imageUrl = imageUrl ?: "",
        isBookmarked = false,
        isBreaking = isBreaking,
        isTop = isTop,
        isRecommended = isRecommended
    )