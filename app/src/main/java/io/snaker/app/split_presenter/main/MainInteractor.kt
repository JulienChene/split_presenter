package io.snaker.app.split_presenter.main

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.snaker.app.split_presenter.storage.Repository

class MainInteractor {

    val repository = Repository.instance

    fun getMatchBadgeCount(): Flowable<Int> {
        return repository.getNotificationBadgeUpdates()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
