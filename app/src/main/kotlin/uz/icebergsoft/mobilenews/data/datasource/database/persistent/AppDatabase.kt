package uz.icebergsoft.mobilenews.data.datasource.database.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.icebergsoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val articleEntityDao: ArticleEntityDao

    companion object {

        private const val DATABASE_NAME: String = "news_database"

        fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addMigrations(*AppDatabaseMigrations.migrations)
                .build()
    }
}