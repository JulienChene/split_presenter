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
        view.layoutParams.apply {
            width = 0
            height = 0
        }

        startConstraintSet.clone(this)
        startConstraintSet.connect(view.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP, this.measuredHeight)
        startConstraintSet.connect(view.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM, -this.measuredHeight)
//        startConstraintSet.connect(view.id, ConstraintSet.TOP, this.id, ConstraintSet.TOP)
//        startConstraintSet.connect(view.id, ConstraintSet.BOTTOM, this.id, ConstraintSet.BOTTOM)
        startConstraintSet.connect(view.id, ConstraintSet.START, this.id, ConstraintSet.START)
        startConstraintSet.connect(view.id, ConstraintSet.END, this.id, ConstraintSet.END)
//        startConstraintSet.setVerticalBias(view.id, 1.0f)
        startConstraintSet.applyTo(this)

        Handler().postDelayed({
            val endConstraintSet = ConstraintSet()
            endConstraintSet.clone(startConstraintSet)
            endConstraintSet.setMargin(view.id, ConstraintSet.TOP, 0)
            endConstraintSet.setMargin(view.id, ConstraintSet.BOTTOM, 0)
//            startConstraintSet.setVerticalBias(view.id, 0f)

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
                override fun onTransitionStart(transition: Transition?) {
                }
            })
            TransitionManager.beginDelayedTransition(this, transition)
            endConstraintSet.applyTo(this)
        }, 100)
    }
}
