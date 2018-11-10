package io.snaker.app.split_presenter.discover

import io.reactivex.disposables.CompositeDisposable
import io.snaker.app.split_presenter.storage.Potential
import java.lang.ref.WeakReference

class DiscoverPresenter {

    val interactor = DiscoverInteractor()
    var weakView: WeakReference<View>? = null

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

    fun getNewPotentials() {
        disposable?.add(interactor.createNewPotentials()
                .subscribe({
                    // We just generated a new set, show new potential
                    onPotentialInteraction()
                }, {

                })
        )
    }

    fun onPotentialInteraction() {
        disposable?.add(interactor.getPotential()
                .subscribe({
                    potential ->
                    val view = weakView?.get() ?: return@subscribe
                    interactor.removePotential(potential).subscribe()
                    view.showPotential(potential, true)
                }, {

                }, {
                    weakView?.get()?.showEmptyState()
                })
        )
    }

    interface View {
        fun showEmptyState()
        fun showPotential(potential: Potential, shouldTakeover: Boolean)
    }
}
