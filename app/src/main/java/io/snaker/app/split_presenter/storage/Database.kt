package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [
    Match::class,
    Potential::class
], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun matches(): MatchesDao
    abstract fun potentials(): PotentialDao
}
