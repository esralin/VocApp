package com.example.vocapp


import androidx.room.*


@Dao
interface WordDao {

    @Insert
    fun insert(user: User)

    @Insert
    fun insertScore(score: Score)

    @Query("SELECT * FROM score_table")
    fun getScore(): Score

    @Query("SELECT * FROM user_table where email = :key")
    fun isTaken(key: String): Boolean

    @Query("SELECT * FROM user_table where email = :email AND password = :password")
    fun login(email: String,password: String): Boolean

    @Query("SELECT * FROM user_table where email = :key")
    fun getUser(key: String): User

   @Query("SELECT * FROM word_table WHERE level = :key ORDER BY RANDOM() LIMIT 1")
   fun getQuestion(key: String): Word

   @Query("SELECT * FROM word_table WHERE definition = :key")
   fun getWordByDefinition(key: String): Word

    @Query("SELECT * FROM word_table WHERE word = :key")
    fun getWordByAnswer(key: String): Word



}