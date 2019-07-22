package com.example.santoshlokhande.ratingapp.db


import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.example.santoshlokhande.ratingapp.db.dao.BookDao
import com.example.santoshlokhande.ratingapp.db.entity.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun noteDao(): BookDao

    companion object {
        private var instance: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase? {
            if (instance == null) {
                synchronized(BookDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        BookDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }

    }
    class PopulateDbAsyncTask(db: BookDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.noteDao()

        override fun doInBackground(vararg p0: Unit?) {
            noteDao?.insert(Book("White Tiger", 3f))
            noteDao?.insert(Book("Nectar in a Sieve", 5f))
            noteDao?.insert(Book("The Great Indian Novel", 3f))
            noteDao?.insert(Book("Train To Pakisthan", 5f))
            noteDao?.insert(Book("Palace Of Illusions", 3f))
            noteDao?.insert(Book("The Guide", 4f))
            noteDao?.insert(Book("In Custody", 3f))
            noteDao?.insert(Book("The God Of Small Things", 5f))
            noteDao?.insert(Book("A Fine Balance", 2f))
            noteDao?.insert(Book("A Suitable Boy", 4f))
        }
    }

}