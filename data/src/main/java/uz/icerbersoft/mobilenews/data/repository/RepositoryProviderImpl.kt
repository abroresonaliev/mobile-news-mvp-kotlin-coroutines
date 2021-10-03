package uz.icerbersoft.mobilenews.data.repository

import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icerbersoft.mobilenews.data.datasource.rest.service.RestService
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepositoryImpl

internal class RepositoryProviderImpl(
    private val articleEntityDao: ArticleEntityDao,
    private val restService: RestService
) : RepositoryProvider {

    override val articleRepository: ArticleRepository by lazy {
        ArticleRepositoryImpl(articleEntityDao, restService)
    }
}