package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.*
import io.reactivex.Maybe

@Dao
abstract class PotentialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(data: Potential)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsertMany(vararg data: Potential)

    @Delete
    abstract fun delete(data: Potential)

    @Query("SELECT * FROM potential ORDER BY userId LIMIT 1")
    abstract fun getPotential(): Maybe<Potential>
}
