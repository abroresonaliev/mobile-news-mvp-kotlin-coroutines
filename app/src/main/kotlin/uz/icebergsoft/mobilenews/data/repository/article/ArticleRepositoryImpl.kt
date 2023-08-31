package uz.icebergsoft.mobilenews.data.repository.article

import kotlinx.coroutines.flow.*
import uz.icebergsoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icebergsoft.mobilenews.data.datasource.network.service.ArticleRestService
import uz.icebergsoft.mobilenews.data.mapper.entityToArticle
import uz.icebergsoft.mobilenews.data.mapper.mapTo
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.entity.pagination.PaginationData
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.data.utils.mapToPaginationData
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleEntityDao: ArticleEntityDao,
    private val articleRestService: ArticleRestService
) : ArticleRepository {

    override fun getArticle(articleId: String): Flow<Article> {
        return articleEntityDao.getArticleEntityById(articleId).map { it.entityToArticle() }
    }

    override fun getBreakingNewsArticles(
        country: String,
        category: String,
        sortBy: String
    ): Flow<ArticleListWrapper> {
        return articleRestService
            .getArticles(
                country = country,
                category = category,
                sortBy = sortBy
            )
            .onEach { it ->
                it.articles.forEach {
                    articleEntityDao
                        .updateArticle(
                            it.mapTo(country = country, category = category, isBreaking = true)
                        )
                }
            }
            .map { it -> it.articles.mapNotNull { it.url } }
            .catch {
                if (it is ConnectException || it is UnknownHostException) emit(listOf())
                else throw it
            }
            .flatMapConcat { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getTopArticles(
        country: String,
        category: String,
        sortBy: String
    ): Flow<ArticleListWrapper> {
        return articleRestService
            .getArticles(country = country, category = category, sortBy = sortBy)
            .onEach { it ->
                it.articles.forEach {
                    articleEntityDao
                        .updateArticle(
                            it.mapTo(country = country, category = category, isTop = true)
                        )
                }
            }
            .map { it -> it.articles.mapNotNull { it.url } }
            .catch {
                if (it is ConnectException || it is UnknownHostException) emit(listOf())
                else throw it
            }
            .flatMapConcat { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getRecommendedArticles(
        country: String, category: String, sortBy: String
    ): Flow<ArticleListWrapper> {
        return articleRestService
            .getArticles(country = country, category = category, sortBy = sortBy)
            .onEach { it ->
                it.articles.forEach {
                    articleEntityDao
                        .updateArticle(
                            it.mapTo(country = country, category = category, isTop = true)
                        )
                }
            }
            .map { it -> it.articles.mapNotNull { it.url } }
            .catch {
                if (it is ConnectException || it is UnknownHostException) emit(listOf())
                else throw it
            }
            .flatMapConcat { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getSavedBreakingArticles(page: Int, perPage: Int): Flow<PaginationData<Article>> {
        TODO("Not yet implemented")
    }

    override fun getSavedTopArticles(page: Int, perPage: Int): Flow<PaginationData<Article>> {
        TODO("Not yet implemented")
    }

    override fun getSavedRecommendedArticles(
        page: Int,
        perPage: Int
    ): Flow<PaginationData<Article>> {
        return articleEntityDao.getRecommendedArticleEntities(perPage, perPage * page - 1)
            .map { list -> list.map { it.entityToArticle() } }
            .map { it.mapToPaginationData(page, perPage) }
    }

    override fun getSavedReadLaterArticles(
        page: Int,
        perPage: Int
    ): Flow<PaginationData<Article>> {
        return articleEntityDao.getSavedReadLaterArticles(perPage, perPage * page - 1)
            .map { list -> list.map { it.entityToArticle() } }
            .map { it.mapToPaginationData(page, perPage) }
    }

    override fun updateBookmark(articleId: String, isBookmarked: Boolean): Flow<Unit> {
        return flow { emit(articleEntityDao.updateBookmark(articleId, isBookmarked)) }
    }
}