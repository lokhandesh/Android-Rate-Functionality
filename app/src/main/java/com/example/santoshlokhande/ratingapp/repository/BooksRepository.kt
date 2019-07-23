package com.example.santoshlokhande.ratingapp.repository

import android.app.Application
import android.arch.lifecycle.LiveData
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
        allBooks = bookDao.getAllNotes()
    }

    fun update(book: Book) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(bookDao).execute(book)
    }

    fun getAllBookList(): LiveData<List<Book>> {
        return allBooks
    }

    fun insert(book: Book) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(bookDao).execute(book)
    }

    private class UpdateNoteAsyncTask(bookDao: BookDao) : AsyncTask<Book, Unit, Unit>() {
        val bookDao = bookDao

        override fun doInBackground(vararg p0: Book) {
            bookDao.updateNotes(p0[0].rating,p0[0].title)
        }
    }

    private class InsertNoteAsyncTask(bookDao: BookDao) : AsyncTask<Book, Unit, Unit>() {
        val bookDao = bookDao

        override fun doInBackground(vararg p0: Book?) {
            bookDao.insert(p0[0]!!)
        }

    }

}