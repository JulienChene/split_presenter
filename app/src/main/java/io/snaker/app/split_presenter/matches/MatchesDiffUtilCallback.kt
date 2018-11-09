package io.snaker.app.split_presenter.matches

import android.support.v7.util.DiffUtil

class MatchesDiffUtilCallback(
        val oldMatches: List<Int>,
        val newMatches: List<Int>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMatches[oldItemPosition] == newMatches[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldMatches.size
    }

    override fun getNewListSize(): Int {
        return newMatches.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMatches[oldItemPosition] == newMatches[newItemPosition]
    }
}
