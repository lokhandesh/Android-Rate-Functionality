package com.example.santoshlokhande.ratingapp.repository

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class BooksRepositoryTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var application : Application

    @Mock
    lateinit var bookViewModel: BookViewModel

    @Mock
    lateinit var repository: BooksRepository

    @Mock
    lateinit var book: Book

    @Mock
    lateinit var allBooks: LiveData<List<Book>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.bookViewModel = BookViewModel(application)
        this.allBooks
    }

    @Test
    fun getAllBookList(){

        var bookist=repository.getAllBookList()
        `when`(bookist).thenReturn(this.allBooks)

    }

    @Test
    fun update() {
        repository.update(book)
    }

}