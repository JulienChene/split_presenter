package io.snaker.app.split_presenter.discover

import io.snaker.app.split_presenter.storage.Potential
import java.util.*

class DiscoverInteractor {

    val potentialList = (0..10)
            .toList()
            .map { Potential(UUID.randomUUID().toString()) }
            .toMutableList()

    fun getPotential(): Potential? {
        return potentialList.firstOrNull()
    }

    fun removePotential() {
        potentialList.removeAt(0)
    }
}
