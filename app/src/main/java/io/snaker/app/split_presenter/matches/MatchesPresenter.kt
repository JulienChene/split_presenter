package io.snaker.app.split_presenter.matches

import android.support.v7.util.DiffUtil
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.lang.ref.WeakReference

class MatchesPresenter {

    var weakView: WeakReference<View>? = null
    val interactor = MatchesInteractor()
    var disposable: CompositeDisposable? = null

    fun onResume(view: View) {
        weakView = WeakReference(view)
        disposable = CompositeDisposable()
    }

    fun onPause() {
        weakView?.clear()
        weakView = null

        if (disposable?.isDisposed != false) disposable?.dispose()
        disposable = null
    }

    fun getMatches() {
        disposable?.add(interactor.getMatchList()
                .subscribe({
                    matchList ->
                    getMatchDiffUtil(matchList)
                }, {
                    error ->
                    Timber.e(error)
                })
        )
    }

    fun getMatchDiffUtil(newMatchList: List<Int>) {
        val oldList = weakView?.get()?.getAdapterMatchList() ?: return
        disposable?.add(interactor.getMatchDiffResult(oldList, newMatchList)
                .subscribe({
                    diff ->
                    weakView?.get()?.showMatches(diff, newMatchList)
                }, {

                })
        )
    }

    interface View {
        fun getAdapterMatchList(): List<Int>
        fun showMatches(diffUtil: DiffUtil.DiffResult, newMatchList: List<Int>)
    }
}
