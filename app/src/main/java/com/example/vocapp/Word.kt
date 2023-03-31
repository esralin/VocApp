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

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var fullName: String = "",
    var email: String = "",
    var password: String = ""
)

@Entity(tableName = "score_table")
data class Score(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var score: Double = 0.0
)



