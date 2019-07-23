package com.example.santoshlokhande.ratingapp

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import com.example.santoshlokhande.ratingapp.db.BookDatabase
import com.example.santoshlokhande.ratingapp.db.dao.BookDao
import com.example.santoshlokhande.ratingapp.db.entity.Book
import com.example.santoshlokhande.ratingapp.viewmodel.BookViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

@RunWith(JUnit4::class)
class BookDaoTest {

    @Mock
    lateinit var bookDao: BookDao

    @Mock
    lateinit var bookDatabase: BookDatabase

    @Mock
    lateinit var application : Application

    @Mock
    lateinit var observer: Observer<List<Book>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        bookDatabase = Room.inMemoryDatabaseBuilder(this.application,BookDatabase::class.java)
                .allowMainThreadQueries().build();
        bookDao = bookDatabase.noteDao();

    }

    @Test
    fun insert(){

        val book = Book("Testing Book",3f)
        //bookDao.getAllNotes().observeForever(observer);
        val userId=bookDao.insert(book)
        val userFromDb = bookDao.getAllNotes()

        Log.d("Here","VALUE"+bookDatabase.noteDao().insert(book))


        //bookDao?.insert(book)

      //  assertEquals(userId, 1)
      //  assertEquals(userFromDb?., user.userName)

      //  verify(observer).onChanged(Collections.singletonList(book))

    }

    @After
    fun close(){
        bookDatabase.close()
    }



}