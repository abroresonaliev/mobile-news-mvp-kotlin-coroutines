package uz.icerbersoft.mobilenews.data.datasource.database

import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao

internal interface DatabaseProvider {

    val articleEntityDao: ArticleEntityDao
}