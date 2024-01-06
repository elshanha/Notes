package com.example.notes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Entity (tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo(name = "noteTitle") val noteTitle: String,
    @ColumnInfo(name = "noteContent") val noteContent: String,
    @ColumnInfo(name = "timestamp") var timestamp: String,
    @ColumnInfo(name = "isfavorite") var isFavorite : Boolean
) : Parcelable {

}
