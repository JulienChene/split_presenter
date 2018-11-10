package io.snaker.app.split_presenter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import io.snaker.app.split_presenter.storage.Repository
import timber.log.Timber

class App: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        context = this.applicationContext

        Repository.instance.initRepository()
    }
}
