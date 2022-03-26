package uz.icerbersoft.mobilenews.app.application.di

import dagger.Module
import dagger.Provides
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepository
import javax.inject.Singleton

@Module(includes = [ApplicationDaggerModuleRepository.Provider::class])
object ApplicationDaggerModuleRepository {

    @Module
    object Provider {

        @JvmStatic
        @Provides
        @Singleton
        fun articleRepository(
            repositoryProvider: RepositoryProvider
        ): ArticleRepository = repositoryProvider.articleRepository

    }
}