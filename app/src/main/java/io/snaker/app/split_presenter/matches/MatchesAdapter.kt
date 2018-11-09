package io.snaker.app.split_presenter.matches

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.snaker.app.split_presenter.R

class MatchesAdapter(
        var matchList: List<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.match_view_holder, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MatchViewHolder).textView.text = (matchList[position]).toString()
    }

    inner class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val textView: TextView = itemView.findViewById(R.id.text)
    }

    fun updateList(diffUtil: DiffUtil.DiffResult, newMatchList: List<Int>) {
        matchList = newMatchList
        diffUtil.dispatchUpdatesTo(this)
    }
}
