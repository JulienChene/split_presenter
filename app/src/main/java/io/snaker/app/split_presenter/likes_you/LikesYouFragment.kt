package io.snaker.app.split_presenter.likes_you

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.snaker.app.split_presenter.R

class LikesYouFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.likes_you_fragment, container, false)
    }
}
