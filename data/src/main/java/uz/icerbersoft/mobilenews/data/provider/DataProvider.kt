package uz.icerbersoft.mobilenews.data.provider

import android.content.Context
import uz.icerbersoft.mobilenews.data.datasource.DataSourceProvider
import uz.icerbersoft.mobilenews.data.datasource.database.DatabaseProvider
import uz.icerbersoft.mobilenews.data.repository.RepositoryProvider
import uz.icerbersoft.mobilenews.data.repository.RepositoryProviderImpl

class DataProvider(context: Context) {
    private val dataSourceProvider by lazy {
        DataSourceProvider(context = context)
    }

    val repositoryProvider: RepositoryProvider by lazy {
        val databaseProvider: DatabaseProvider =
            dataSourceProvider.databaseProvider

        return@lazy RepositoryProviderImpl(
            articleEntityDao = databaseProvider.articleEntityDao,
            restService = dataSourceProvider.restProvider.restService
        )
    }
}