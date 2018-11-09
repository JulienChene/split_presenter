package io.snaker.app.split_presenter.discover

import io.snaker.app.split_presenter.storage.Potential
import java.lang.ref.WeakReference

class DiscoverPresenter {

    val interactor = DiscoverInteractor()
    var weakView: WeakReference<View>? = null

    fun onResume(view: View) {
        weakView = WeakReference(view)
    }

    fun onPause() {
        weakView?.clear()
        weakView = null
    }

    fun onDiscoverInteraction() {
        val potential = interactor.getPotential()
        val view = weakView?.get() ?: return
        if (potential == null) {
            view.showEmpty()
        } else {
            interactor.removePotential()
            view.showPotential(potential, true)
        }
    }

    interface View {
        fun showEmpty()
        fun showPotential(potential: Potential, shouldTakeover: Boolean)
    }
}
