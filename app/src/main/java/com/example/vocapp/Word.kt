package com.example.vocapp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var level: String = "",
    var word: String = "",
    var definition: String = ""
)


