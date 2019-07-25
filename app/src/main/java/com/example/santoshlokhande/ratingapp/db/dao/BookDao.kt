package com.example.santoshlokhande.ratingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.santoshlokhande.ratingapp.db.entity.Book

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM books_table ")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("UPDATE books_table SET rating = :rating WHERE title =:title")
    fun updateBooks(rating: Float, title: String)

}