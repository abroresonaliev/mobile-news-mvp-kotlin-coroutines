package uz.icerbersoft.mobilenews.data.datasource.database

import android.content.Context
import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icerbersoft.mobilenews.data.datasource.database.persistent.AppDatabase

internal class DatabaseProviderImpl(context: Context) : DatabaseProvider {
    private val appDatabase by lazy { AppDatabase.create(context) }

    override val articleEntityDao: ArticleEntityDao
        get() = appDatabase.articleEntityDao
}