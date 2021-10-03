package uz.icerbersoft.mobilenews.data.datasource.database.base

import androidx.room.*

internal abstract class BaseDao<T : Any> {

    @Delete
    abstract fun delete(collection: Collection<T>)

    @Delete
    abstract fun delete(value: T)

    @Deprecated("")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(value: T): Long

    @Deprecated("")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(collection: Collection<T>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(value: T): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(collection: Collection<T>)

    @Transaction
    open fun upsert(value: T): Long {
        val insertResult: Long = insert(value)
        return if (insertResult == -1L) update(value)
        else insertResult
    }
}