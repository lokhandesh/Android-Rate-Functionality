package com.example.santoshlokhande.ratingapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.repository.BooksRepository
import junit.framework.Assert
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class BookViewModelTest{
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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.bookViewModel = BookViewModel(application)
    }


    @Test
    fun getAllBooks(){

        var boolist=bookViewModel.getAllBooks()
        assertNotNull(boolist)

    }

    @Test
    fun getRandomNumber(){
        var randomValue=this.bookViewModel.getRandomValue(10)
        assertNotNull(randomValue)

    }

    @Test
    fun update() {
        bookViewModel.update(book)
    }
}