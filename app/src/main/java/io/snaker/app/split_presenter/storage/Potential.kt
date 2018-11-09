package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "potential")
data class Potential(
        @PrimaryKey() val userId: String = UUID.randomUUID().toString()
) {
}
