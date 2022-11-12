package uz.icebergsoft.mobilenews.presentation.application.di.data

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.icebergsoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icebergsoft.mobilenews.data.datasource.database.persistent.AppDatabase
import javax.inject.Singleton

@Module
internal object DataDaggerModuleDao {

    @JvmStatic
    @Provides
    @Singleton
    fun appDatabase(
        context: Context
    ): AppDatabase =
        AppDatabase.create(context)

    @JvmStatic
    @Provides
    @Singleton
    fun articleEntityDao(
        appDatabase: AppDatabase
    ): ArticleEntityDao =
        appDatabase.articleEntityDao
}