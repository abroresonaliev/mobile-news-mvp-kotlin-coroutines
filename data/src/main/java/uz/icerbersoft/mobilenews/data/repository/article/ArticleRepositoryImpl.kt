package uz.icerbersoft.mobilenews.data.repository.article

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icerbersoft.mobilenews.data.datasource.rest.service.RestService
import uz.icerbersoft.mobilenews.data.mapper.mapToArticle
import uz.icerbersoft.mobilenews.data.mapper.mapToEntity
import uz.icerbersoft.mobilenews.data.model.article.Article
import uz.icerbersoft.mobilenews.data.model.article.ArticleListWrapper
import java.net.ConnectException

internal class ArticleRepositoryImpl(
    private val articleEntityDao: ArticleEntityDao,
    private val restService: RestService
) : ArticleRepository {

    override fun getArticle(articleId: Long): Flow<Article> {
        return articleEntityDao.getArticleEntityById(articleId).map { it.mapToArticle() }
    }

    @FlowPreview
    override fun getArticles(): Flow<ArticleListWrapper> {
        return restService.getBreakingArticles()
            .onEach { it -> it.articles.forEach { articleEntityDao.upsert(it.mapToEntity()) } }
            .map { it.articles.isNotEmpty() }
            .catch {
                if (it is ConnectException) emit(false)
                else throw it
            }
            .flatMapConcat { isLoaded ->
                articleEntityDao.getArticleEntities()
                    .map { list -> list.map { it.mapToArticle() } }
                    .map { ArticleListWrapper(it, !isLoaded) }
            }
    }

    @FlowPreview
    override fun getBreakingNewsArticles(): Flow<ArticleListWrapper> {
        return restService.getBreakingArticles()
            .onEach { it.articles.forEach { articleEntityDao.upsert(it.mapToEntity()) } }
            .map { it.articles.isNotEmpty() }
            .catch {
                if (it is ConnectException) emit(false)
                else throw it
            }
            .flatMapConcat { isLoaded ->
                articleEntityDao.getArticleEntities()
                    .map { list -> list.map { it.mapToArticle() } }
                    .map { ArticleListWrapper(it, !isLoaded) }
            }
    }

    @FlowPreview
    override fun getTopArticles(): Flow<ArticleListWrapper> {
        return restService.getTopArticles()
            .onEach { it -> it.articles.forEach { articleEntityDao.upsert(it.mapToEntity()) } }
            .map { it.articles.isNotEmpty() }
            .catch {
                if (it is ConnectException) emit(false)
                else throw it
            }
            .flatMapConcat { isLoaded ->
                articleEntityDao.getArticleEntities()
                    .map { list -> list.map { it.mapToArticle() } }
                    .map { ArticleListWrapper(it, !isLoaded) }
            }
    }

    @FlowPreview
    override fun getRecommendedArticles(): Flow<ArticleListWrapper> {
        return restService.getRecommendedArticles()
            .onEach { it.articles.forEach { articleEntityDao.upsert(it.mapToEntity()) } }
            .map { it.articles.isNotEmpty() }
            .catch {
                if (it is ConnectException) emit(false)
                else throw it
            }
            .flatMapConcat { isLoaded ->
                articleEntityDao.getArticleEntities()
                    .map { list -> list.map { it.mapToArticle() } }
                    .map { ArticleListWrapper(it, !isLoaded) }
            }
    }

    @FlowPreview
    override fun getReadLaterArticles(): Flow<ArticleListWrapper> {
        return articleEntityDao.getArticleEntitiesByBookmark(true)
            .map { list -> list.map { it.mapToArticle() } }
            .map { ArticleListWrapper(it, true) }
    }

    override fun updateBookmark(articleId: Long, isBookmarked: Boolean): Flow<Unit> {
        return flow { emit(articleEntityDao.updateBookmark(articleId, isBookmarked)) }
    }
}