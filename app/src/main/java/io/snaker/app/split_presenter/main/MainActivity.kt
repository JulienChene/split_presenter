package io.snaker.app.split_presenter.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.snaker.app.split_presenter.R
import io.snaker.app.split_presenter.discover.DiscoverFragment
import io.snaker.app.split_presenter.likes_you.LikesYouFragment
import io.snaker.app.split_presenter.matches.MatchesFragment
import io.snaker.app.split_presenter.settings.SettingsFragment
import io.snaker.app.split_presenter.structure.Feature
import io.snaker.app.split_presenter.views.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnFeatureTapped, MainPresenter.View {

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_view.listener = this

        showFeature(Feature.Discover)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume(this)
        presenter.getMatchBadgeCount()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onFeatureTapped(feature: Feature) {
        presenter.onFeatureTapped(feature)
    }

    override fun showFeature(feature: Feature) {
        val fragment = when (feature) {
            Feature.Discover -> DiscoverFragment()
            Feature.LikesYou -> LikesYouFragment()
            Feature.Matches -> MatchesFragment()
            Feature.Settings -> SettingsFragment()
        }

        replaceFragment(fragment)
    }

    private fun replaceFragment(newFragment: Fragment) {
        if (isFinishing) return
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commitAllowingStateLoss()
    }

    override fun showBadgeCountForFeature(feature: Feature, badgeCount: Int) {
        when (feature) {
            Feature.Matches -> matches_activity.text = badgeCount.toString()
            else -> return
        }
    }
}
