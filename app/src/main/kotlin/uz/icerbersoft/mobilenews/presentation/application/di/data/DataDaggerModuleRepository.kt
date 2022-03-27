package uz.icerbersoft.mobilenews.presentation.application.di.data

import dagger.Module
import dagger.Provides
import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icerbersoft.mobilenews.data.datasource.rest.service.ArticleRestService
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepositoryImpl
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import javax.inject.Singleton

@Module
internal object DataDaggerModuleRepository {

    @JvmStatic
    @Provides
    @Singleton
    fun articleRepository(
        articleEntityDao: ArticleEntityDao,
        articleRestService: ArticleRestService
    ): ArticleRepository =
        ArticleRepositoryImpl(articleEntityDao, articleRestService)
}