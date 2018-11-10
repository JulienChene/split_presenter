package io.snaker.app.split_presenter.views

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View

class TakeoverViewManager @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var activeView: View? = null

    fun viewTakeover(view: View) {
        val startConstraintSet = ConstraintSet()
        this.addView(view)

        startConstraintSet.clone(this)
        startConstraintSet.connect(view.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, this.measuredHeight)
        startConstraintSet.applyTo(this)

        Handler().postDelayed({
            val endConstraintSet = ConstraintSet()
            endConstraintSet.clone(startConstraintSet)
            endConstraintSet.setMargin(view.id, ConstraintSet.TOP, 0)

            val transition = AutoTransition()
            transition.duration = 500
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition?) {
                    if (activeView != null) removeView(activeView)
                    activeView = view
                }
                override fun onTransitionResume(transition: Transition?) {}
                override fun onTransitionPause(transition: Transition?) {}
                override fun onTransitionCancel(transition: Transition?) {}
                override fun onTransitionStart(transition: Transition?) {}
            })
            TransitionManager.beginDelayedTransition(this, transition)
            endConstraintSet.applyTo(this)
        }, 100)
    }
}
