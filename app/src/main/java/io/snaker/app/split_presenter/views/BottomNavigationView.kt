package io.snaker.app.split_presenter.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import io.snaker.app.split_presenter.R
import io.snaker.app.split_presenter.structure.Feature
import kotlinx.android.synthetic.main.navigation_view.view.*

class BottomNavigationView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    var listener: OnFeatureTapped? = null

    init {
        View.inflate(context, R.layout.navigation_view, this)

        discover_button.setOnClickListener(this)
        likes_you_button.setOnClickListener(this)
        matches_button.setOnClickListener(this)
        settings_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val feature = when (view) {
            discover_button -> Feature.Discover
            likes_you_button -> Feature.LikesYou
            matches_button -> Feature.Matches
            settings_button -> Feature.Settings
            else -> return
        }

        listener?.onFeatureTapped(feature)
    }


    interface OnFeatureTapped {
        fun onFeatureTapped(feature: Feature)
    }
}
