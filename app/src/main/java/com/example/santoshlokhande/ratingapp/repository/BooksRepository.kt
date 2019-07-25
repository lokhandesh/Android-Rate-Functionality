package com.example.santoshlokhande.ratingapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import android.os.AsyncTask
import com.example.santoshlokhande.ratingapp.db.BookDatabase
import com.example.santoshlokhande.ratingapp.db.dao.BookDao
import com.example.santoshlokhande.ratingapp.db.entity.Book

class BooksRepository(application: Application) {

    private var bookDao: BookDao
    private var allBooks: LiveData<List<Book>>

    init {
        val database: BookDatabase = BookDatabase.getInstance(
            application
        )!!
        bookDao = database.noteDao()
        allBooks = bookDao.getAllBooks()
    }

    fun update(book: Book) {
        val updateNoteAsyncTask = UpdateBookAsyncTask(bookDao).execute(book)
    }

    fun getAllBookList(): LiveData<List<Book>> {
        return allBooks
    }

    private class UpdateBookAsyncTask(bookDao: BookDao) : AsyncTask<Book, Unit, Unit>() {
        val bookDao = bookDao

        override fun doInBackground(vararg p0: Book) {
            bookDao.updateBooks(p0[0].rating,p0[0].title)
        }
    }

   /* private class InsertBookAsyncTask(bookDao: BookDao) : AsyncTask<Book, Unit, Unit>() {
        val bookDao = bookDao

        override fun doInBackground(vararg p0: Book?) {
            bookDao.insert(p0[0]!!)
        }

    }*/

}