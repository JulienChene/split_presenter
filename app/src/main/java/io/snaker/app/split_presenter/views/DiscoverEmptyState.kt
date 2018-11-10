package io.snaker.app.split_presenter.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import io.snaker.app.split_presenter.R
import java.lang.ref.WeakReference

class DiscoverEmptyState @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var weakListener: WeakReference<OnEmptyStateClick>? = null

    init {
        View.inflate(context, R.layout.discover_empty_state, this)
        id = View.generateViewId()
        this.setOnClickListener {
            weakListener?.get()?.onGenerateNewPotentialsClicked()
        }
    }

    fun setListener(listener: OnEmptyStateClick) {
        if (weakListener?.get() != null) weakListener?.clear()
        weakListener = WeakReference(listener)
    }

    interface OnEmptyStateClick {
        fun onGenerateNewPotentialsClicked()
    }
}
