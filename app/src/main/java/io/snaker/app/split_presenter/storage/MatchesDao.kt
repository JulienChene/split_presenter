package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.*
import io.reactivex.Flowable
import timber.log.Timber

@Dao
abstract class MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(data: Match)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsertMany(vararg data: Match)

    @Query("SELECT * from matches")
    abstract fun getAllMatchesUpdates(): Flowable<List<Match>>

    @Query("SELECT * from matches")
    abstract fun getAllMatches(): List<Match>

    @Query("SELECT COUNT() from matches")
    abstract fun getMatchCountUpdate(): Flowable<Int>

    @Query("SELECT COUNT() from matches")
    abstract fun getMatchCount(): Int

    @Query("DELETE FROM matches")
    abstract fun nukeTableContent()

    @Transaction
    open fun addMatch() {
        val matchCount = getMatchCount()
        val match = Match(matchCount + 1)
        if (matchCount >= 10) {
            Timber.e("Nuking matches table !!!!")
            Timber.e("match list ${getAllMatches()}")
            nukeTableContent()
        }
        upsert(match)
    }
}

