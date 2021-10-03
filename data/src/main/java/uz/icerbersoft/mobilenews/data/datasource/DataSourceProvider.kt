package uz.icerbersoft.mobilenews.data.datasource

import android.content.Context
import uz.icerbersoft.mobilenews.data.datasource.database.DatabaseProvider
import uz.icerbersoft.mobilenews.data.datasource.database.DatabaseProviderImpl
import uz.icerbersoft.mobilenews.data.datasource.rest.RestProvider
import uz.icerbersoft.mobilenews.data.datasource.rest.RestProviderImpl

internal class DataSourceProvider(
    private val context: Context
) {

    val databaseProvider: DatabaseProvider by lazy {
        DatabaseProviderImpl(context)
    }

    val restProvider: RestProvider by lazy {
        RestProviderImpl()
    }
}