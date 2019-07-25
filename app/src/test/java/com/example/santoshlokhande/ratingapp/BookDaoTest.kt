package com.example.santoshlokhande.ratingapp

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.util.Log
import com.example.santoshlokhande.ratingapp.db.BookDatabase
import com.example.santoshlokhande.ratingapp.db.dao.BookDao
import com.example.santoshlokhande.ratingapp.db.entity.Book
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class BookDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var bookDao: BookDao

    @Mock
    lateinit var bookDatabase: BookDatabase

    @Mock
    lateinit var application : Application

    @Mock
    lateinit var book: Book

    @Mock
    lateinit var observer: Observer<List<Book>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        bookDatabase = Room.inMemoryDatabaseBuilder(this.application,BookDatabase::class.java)
                .allowMainThreadQueries().build();
        bookDao = bookDatabase.noteDao();
        bookDatabase.isOpen


    }

    @Test
    fun insert(){

        Thread {
            val book = Book("Testing Book", 3f)
            //bookDao.getAllNotes().observeForever(observer);
            val userId = bookDao.insert(book)
            val userFromDb = bookDao.getAllBooks()

            assertEquals(userId, 1)
        }.start()

    }

    @Test
    fun getAllBooks(){

        Thread {
            val book = Book("Testing Book", 3f)
            val userFromDb = bookDao.getAllBooks()
            assertEquals(10, userFromDb)
        }.start()

    }

    @Test
    fun update(){

        Thread {
            val userId = bookDao.updateBooks(3f,"A Suitable Boy")
            assertNotNull(userId)

        }.start()

    }

    @After
    fun close(){
        bookDatabase.close()
    }



}