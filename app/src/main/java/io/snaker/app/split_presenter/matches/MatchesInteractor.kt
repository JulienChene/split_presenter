package io.snaker.app.split_presenter.matches

import android.support.v7.util.DiffUtil
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.snaker.app.split_presenter.storage.Repository
import timber.log.Timber

class MatchesInteractor {

    private val repository = Repository.instance

    fun getMatchList(): Flowable<List<Int>> {
        return repository.getMatches()
                .map {
                    matches ->
                    Timber.e("getMatchList: ${matches.size}")
                    matches.map {
                         match ->
                        match.id
                    }
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
    }

    fun getMatchDiffResult(oldList: List<Int>, newList: List<Int>): Single<DiffUtil.DiffResult> {
        return Single.create<DiffUtil.DiffResult> {
            emitter ->
            val callback = MatchesDiffUtilCallback(oldList, newList)
            val result = DiffUtil.calculateDiff(callback)

            if (!emitter.isDisposed) emitter.onSuccess(result)
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
