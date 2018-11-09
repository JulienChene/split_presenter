package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
abstract class PotentialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(data: Potential)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsertMany(vararg data: Potential)

    @Delete
    abstract fun delete(data: Potential)
}
