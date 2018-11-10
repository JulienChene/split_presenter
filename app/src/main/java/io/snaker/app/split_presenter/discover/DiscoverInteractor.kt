package io.snaker.app.split_presenter.discover

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.snaker.app.split_presenter.storage.Potential
import io.snaker.app.split_presenter.storage.Repository
import timber.log.Timber

class DiscoverInteractor {

    val repository = Repository.instance

    fun getPotential(): Maybe<Potential> {
        return repository.getPotential()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun removePotential(potential: Potential): Completable {
        return Completable.create {
            emitter ->
            repository.removePotential(potential)
            Timber.e("potential removed")
            if (!emitter.isDisposed) emitter.onComplete()
        }
                .subscribeOn(Schedulers.computation())

    }

    fun createNewPotentials(): Completable {
        return repository.generatePotentials()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
