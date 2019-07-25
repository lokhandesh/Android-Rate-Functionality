package com.example.santoshlokhande.ratingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.repository.BooksRepository
import java.util.*


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: BooksRepository = BooksRepository(application)

    public var booksList: LiveData<List<Book>> = repository.getAllBookList()

    fun update(book: Book) {
        repository.update(book)
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return booksList
    }

    fun getRandomValue(value:Int):Int{
       return Random().nextInt(value)
    }

}