package uz.icerbersoft.mobilenews.presentation.application.di.data

import dagger.Binds
import dagger.Module
import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepositoryImpl
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.data.repository.settings.SettingsRepository
import uz.icerbersoft.mobilenews.domain.data.repository.settings.SettingsRepositoryImpl

@Module
internal interface DataDaggerModuleRepository {

    @Binds
    fun bindArticleRepository(
        impl: ArticleRepositoryImpl
    ): ArticleRepository

    @Binds
    fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository
}