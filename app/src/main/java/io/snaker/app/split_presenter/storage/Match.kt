package io.snaker.app.split_presenter.storage

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
        @PrimaryKey() val id: Int
) {
}
