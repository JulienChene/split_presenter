package io.snaker.app.split_presenter.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.snaker.app.split_presenter.R
import kotlinx.android.synthetic.main.matches_fragment.*

class MatchesFragment: Fragment(), MatchesPresenter.View {

    val presenter = MatchesPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.matches_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = MatchesAdapter(emptyList())

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
        presenter.getMatches()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showMatches(diffUtil: DiffUtil.DiffResult, newMatchList: List<Int>) {
        (recycler_view.adapter as? MatchesAdapter)?.updateList(diffUtil, newMatchList)
    }

    override fun getAdapterMatchList(): List<Int> {
        return (recycler_view.adapter as? MatchesAdapter)?.matchList ?: emptyList()
    }

}
