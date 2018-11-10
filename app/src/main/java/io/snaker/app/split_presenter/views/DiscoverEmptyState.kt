package io.snaker.app.split_presenter.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import io.snaker.app.split_presenter.R

class DiscoverEmptyState @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var listener: OnEmptyStateClick? = null

    init {
        View.inflate(context, R.layout.discover_empty_state, this)
        id = View.generateViewId()
        this.setOnClickListener {
            listener?.onGenerateNewPotentialsClicked()
        }
    }

    interface OnEmptyStateClick {
        fun onGenerateNewPotentialsClicked()
    }
}
