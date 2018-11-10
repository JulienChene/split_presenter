package io.snaker.app.split_presenter.discover

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.snaker.app.split_presenter.R
import io.snaker.app.split_presenter.storage.Potential
import io.snaker.app.split_presenter.views.DiscoverEmptyState
import io.snaker.app.split_presenter.views.PotentialView
import kotlinx.android.synthetic.main.discover_fragment.*

class DiscoverFragment : Fragment(), DiscoverPresenter.View, DiscoverEmptyState.OnEmptyStateClick {

    val presenter = DiscoverPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.discover_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)

        presenter.onDiscoverInteraction()
        takeover_manager.setOnClickListener {
            presenter.onDiscoverInteraction()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onGenerateNewPotentialsClicked() {
        presenter.getNewPotentials()
    }

    override fun showEmptyState() {
        val context = context ?: return
        val emptyStateView = DiscoverEmptyState(context)
        emptyStateView.listener = this

        takeover_manager.viewTakeover(emptyStateView)
    }

    override fun showPotential(potential: Potential, shouldTakeover: Boolean) {
        val context = context ?: return
        val potentialView = PotentialView(context)
        potentialView.setPotentialId(potential.userId)

        takeover_manager.viewTakeover(potentialView)
    }

}
