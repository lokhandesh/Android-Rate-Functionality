package com.example.santoshlokhande.ratingapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.repository.BooksRepository


class BookViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: BooksRepository =
        BooksRepository(application)

    public var booksList: LiveData<List<Book>> = repository.getAllBookList()

    fun update(book: Book) {
        repository.update(book)
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return booksList
    }
}