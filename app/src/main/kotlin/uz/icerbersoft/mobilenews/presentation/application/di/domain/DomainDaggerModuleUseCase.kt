package uz.icerbersoft.mobilenews.presentation.application.di.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.daynight.DayNightModeUseCase
import uz.icerbersoft.mobilenews.domain.usecase.daynight.DayNightModeUseCaseImpl
import javax.inject.Singleton

@Module(
    includes = [
        DomainDaggerModuleUseCase.Binders::class,
        DomainDaggerModuleUseCase.Providers::class
    ]
)
internal object DomainDaggerModuleUseCase {

    @Module
    interface Binders {

        @Binds
        fun bindArticleDetailUseCase(
            impl: ArticleDetailUseCaseImpl
        ): ArticleDetailUseCase

        @Binds
        fun bindDashboardArticleListUseCase(
            impl: DashboardArticlesUseCaseImpl
        ): DashboardArticlesUseCase

        @Binds
        fun bindReadLaterArticleListUseCase(
            impl: ReadLaterArticlesUseCaseImpl
        ): ReadLaterArticlesUseCase

        @Binds
        fun bindRecommendedArticleListUseCase(
            impl: RecommendedArticlesUseCaseImpl
        ): RecommendedArticlesUseCase

        @Binds
        fun bindDayNightModeUseCase(
            impl: DayNightModeUseCaseImpl
        ): DayNightModeUseCase

    }

    @Module
    object Providers {

        @JvmStatic
        @Provides
        @Singleton
        fun provideBookmarkUseCase(
            articleRepository: ArticleRepository
        ): BookmarkUseCase =
            BookmarkUseCaseImpl(articleRepository)
    }
}