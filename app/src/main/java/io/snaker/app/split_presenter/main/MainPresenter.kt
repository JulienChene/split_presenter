package io.snaker.app.split_presenter.main

import io.reactivex.disposables.CompositeDisposable
import io.snaker.app.split_presenter.structure.Feature
import timber.log.Timber
import java.lang.ref.WeakReference

class MainPresenter {

    val interactor = MainInteractor()
    var currentFeature = Feature.Discover

    var disposable: CompositeDisposable? = null

    var weakView: WeakReference<View>? = null

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

    fun onFeatureTapped(feature: Feature) {
        if (feature == currentFeature) return
        currentFeature = feature

        weakView?.get()?.showFeature(feature)
    }

    fun getMatchBadgeCount() {
        disposable?.add(interactor.getMatchBadgeCount()
                .subscribe({
                    matchBadgeCount ->
                    weakView?.get()?.showBadgeCountForFeature(Feature.Matches, matchBadgeCount)
                }, {
                    error ->
                    Timber.e(error)
                }, {

                }))

    }

    interface View {
        fun showFeature(feature: Feature)
        fun showBadgeCountForFeature(feature: Feature, badgeCount: Int)
    }
}
