package uz.icebergsoft.mobilenews.presentation.application.di.data

import dagger.Binds
import dagger.Module
import uz.icebergsoft.mobilenews.data.repository.article.ArticleRepositoryImpl
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.data.repository.settings.SettingsRepository
import uz.icebergsoft.mobilenews.data.repository.settings.SettingsRepositoryImpl

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