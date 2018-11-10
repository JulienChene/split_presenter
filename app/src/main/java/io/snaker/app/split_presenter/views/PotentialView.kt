package io.snaker.app.split_presenter.views

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import io.snaker.app.split_presenter.R
import kotlinx.android.synthetic.main.potential_view.view.*
import java.lang.ref.WeakReference
import kotlin.random.Random

class PotentialView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var weakListener: WeakReference<OnPotentialViewClick>? = null

    init {
        View.inflate(context, R.layout.potential_view, this)
        id = View.generateViewId()
        this.setBackgroundColor(Color.argb(255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)))

        this.setOnClickListener {
            weakListener?.get()?.onPotentialClicked()
        }
    }

    fun setListener(listener: OnPotentialViewClick) {
        if (weakListener?.get() != null) weakListener?.clear()
        weakListener = WeakReference(listener)
    }

    fun setPotentialId(potentialId: String) {
        potential_id.text = potentialId
    }

    interface OnPotentialViewClick {
        fun onPotentialClicked()
    }
}
