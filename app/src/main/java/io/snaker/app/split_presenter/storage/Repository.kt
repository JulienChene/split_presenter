package io.snaker.app.split_presenter.storage

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.snaker.app.split_presenter.App
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class Repository private constructor() {

    private val database = Room.databaseBuilder(App.context, Database::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    private val matchDao = database.matches()
    private val potentialDao = database.potentials()

    private object Holder { val INSTANCE = Repository() }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    @SuppressLint("CheckResult")
    fun initRepository() {
        // Create matches continuously
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe({
                    matchDao.addMatch()
                }, {

                }, {

                })

        generatePotentials()
                .subscribeOn(Schedulers.computation())
                .subscribe()
    }

    fun generatePotentials(): Completable {
        return Completable.create {
            emitter ->
            val potentialList = (0..10)
                    .toList()
                    .map { Potential(UUID.randomUUID().toString()) }

            potentialDao.upsertMany(*potentialList.toTypedArray())
            if (!emitter.isDisposed) emitter.onComplete()
        }
    }

    fun removePotential(potential: Potential) {
        potentialDao.delete(potential)
    }

    fun getPotential(): Maybe<Potential> {
        Timber.e("getPotential")
        return potentialDao.getPotential()
    }

    fun getNotificationBadgeUpdates(): Flowable<Int> {
        return matchDao.getMatchCountUpdate()
    }

    fun getMatches(): Flowable<List<Match>> {
        return matchDao.getAllMatchesUpdates()
    }
}
