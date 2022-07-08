package com.example.testdroidapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.testdroidapp.model.MyRecord
import java.sql.Blob
import java.util.*

class ExcelWatcherDaoImpl(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    ExcelWatcherDao, SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    private val HEADER = arrayOf(
        "name_rom", "year", "season", "opening", "ending", "inserts", "on_spotify",
        "on_locally", "message", "status", "mal_link", "anilist_link", "spotify_link", "name_eng",
        "extra_link", "thumbnail"
    )

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = """CREATE TABLE $TABLE_NAME (
            |id INTEGER PRIMARY KEY NOT NULL, 
            |name_rom TEXT NOT NULL,
            |year INT NOT NULL,
            |season CHAR(50),
            |opening TEXT,
            |ending TEXT,
            |inserts TEXT,
            |on_spotify INT,
            |on_locally INT,
            |message TEXT,
            |status CHAR(20),
            |mal_link TEXT,
            |anilist_link TEXT,
            |spotify_link TEXT,
            |name_eng TEXT,
            |extra_link TEXT,
            |thumbnail BLOB)""".trimMargin()

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addRecord(myRecord: MyRecord) {

        val values = ContentValues()

        values.put("name_rom", "blob")

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    companion object {
        // here we have defined variables for our database

        private val DATABASE_NAME = "GEEKS_FOR_GEEKS"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "mylist_l"
    }
}