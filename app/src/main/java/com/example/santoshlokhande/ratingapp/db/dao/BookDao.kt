package com.example.santoshlokhande.ratingapp.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.santoshlokhande.ratingapp.db.entity.Book

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("DELETE FROM books_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM books_table ")
    fun getAllNotes(): LiveData<List<Book>>

    @Query("UPDATE books_table SET rating = :rating WHERE title =:title")
    fun updateNotes(rating: Float, title: String)

}