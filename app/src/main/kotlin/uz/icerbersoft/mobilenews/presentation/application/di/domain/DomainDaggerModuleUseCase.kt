package uz.icerbersoft.mobilenews.presentation.application.di.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.icerbersoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticleListUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticleListUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticleListUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticleListUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.article.recommended.RecommendedArticleListUseCase
import uz.icerbersoft.mobilenews.domain.usecase.article.recommended.RecommendedArticleListUseCaseImpl
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import uz.icerbersoft.mobilenews.domain.usecase.bookmark.BookmarkUseCaseImpl
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
            impl: DashboardArticleListUseCaseImpl
        ): DashboardArticleListUseCase

        @Binds
        fun bindReadLaterArticleListUseCase(
            impl: ReadLaterArticleListUseCaseImpl
        ): ReadLaterArticleListUseCase

        @Binds
        fun bindRecommendedArticleListUseCase(
            impl: RecommendedArticleListUseCaseImpl
        ): RecommendedArticleListUseCase

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