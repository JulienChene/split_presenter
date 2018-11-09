package io.snaker.app.split_presenter.storage

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.snaker.app.split_presenter.App
import java.util.concurrent.TimeUnit

class Repository private constructor() {

    private val database = Room.databaseBuilder(App.context, Database::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    private val matchDao = database.matches()

    private object Holder { val INSTANCE = Repository() }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    @SuppressLint("CheckResult")
    fun startCreatingMatches() {
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribe({
                    matchDao.addMatch()
                }, {

                }, {

                })
    }

    fun getNotificationBadgeUpdates(): Flowable<Int> {
        return matchDao.getMatchCountUpdate()
    }

    fun getMatches(): Flowable<List<Match>> {
        return matchDao.getAllMatchesUpdates()
    }
}
