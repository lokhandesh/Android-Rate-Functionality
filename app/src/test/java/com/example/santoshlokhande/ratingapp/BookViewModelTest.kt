package com.example.santoshlokhande.ratingapp

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.util.Log
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel
import junit.framework.Assert.assertEquals
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

    //@get:Rule
    //val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var application : Application

    @Mock
    lateinit var bookViewModel: BookViewModel

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

        val observer = mock(Observer::class.java) as Observer<List<Book>>
        this.bookViewModel.booksList.observeForever(observer)


     //   assertNotNull(this.bookViewModel.allNotes.value)

      //  val si = bookViewModel.booksList.value?.size

        val size=this.bookViewModel.getAllBooks();


        Log.d("SIZE","===="+size)

      //  this.bookViewModel.getAllBooks();
        assertEquals( this.bookViewModel.getAllBooks(), this.bookViewModel.getAllBooks())

    }

    @Test
    fun update() {



    }





}