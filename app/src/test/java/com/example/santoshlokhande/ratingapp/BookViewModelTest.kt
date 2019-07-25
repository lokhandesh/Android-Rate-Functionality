package com.example.santoshlokhande.ratingapp

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.util.Log
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.repository.BooksRepository
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class BookViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var application : Application

    @Mock
    lateinit var bookViewModel: BookViewModel

    @Mock
    lateinit var repository: BooksRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.bookViewModel = BookViewModel(application)
    }


    @Test
    fun getAllBooks(){
       /* Mockito.`when`(this.application.getRepositories(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Book>())
        }*/

        Thread {
            val observer = mock(Observer::class.java) as Observer<List<Book>>
            this.bookViewModel.booksList.observeForever(observer)
            val size = this.bookViewModel.getAllBooks();
            assertNotNull(this.bookViewModel.booksList)
        }.start()

    }

    @Test
    fun getRandomNumber(){
        var randomValue=this.bookViewModel.getRandomValue(10)

        Log.d("HERE","RANDOM VALUE"+randomValue)

        assertNotNull(randomValue)

    }

    @Test
    fun update() {

        Thread{

        }.start()

    }





}