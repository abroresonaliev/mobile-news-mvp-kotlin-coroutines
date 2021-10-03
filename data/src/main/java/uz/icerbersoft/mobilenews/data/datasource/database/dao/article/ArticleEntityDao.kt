package uz.icerbersoft.mobilenews.data.datasource.database.dao.article

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.icerbersoft.mobilenews.data.datasource.database.base.BaseDao
import uz.icerbersoft.mobilenews.data.model.article.ArticleEntity

@Dao
internal abstract class ArticleEntityDao : BaseDao<ArticleEntity>() {

    @Query("SELECT * FROM articles LIMIT 20")
    abstract fun getArticleEntities(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE article_is_bookmarked = :isBookmarked LIMIT 20")
    abstract fun getArticleEntitiesByBookmark(isBookmarked: Boolean): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE article_article_id = :id")
    abstract fun getArticleEntityById(id: Long): Flow<ArticleEntity>

    @Query("UPDATE articles SET article_is_bookmarked = :isBookmarked WHERE article_article_id = :articleId")
    abstract fun updateBookmark(articleId: Long, isBookmarked: Boolean)}