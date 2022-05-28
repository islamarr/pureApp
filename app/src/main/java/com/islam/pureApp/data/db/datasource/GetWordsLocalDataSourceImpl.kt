package com.islam.pureApp.data.db.datasource

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.islam.pureApp.data.db.WordDbHelper
import com.islam.pureApp.data.db.WordEntry
import com.islam.pureApp.domain.entites.Word

class GetWordsLocalDataSourceImpl(context: Context) : GetWordsLocalDataSource {

    private var wordDbHelper: WordDbHelper = WordDbHelper(context)

    override fun getAllWords(): List<Word> {
        val updatedList = ArrayList<Word>()

        val db = wordDbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            WordEntry.COLUMN_Word_TEXT,
            WordEntry.COLUMN_WORD_COUNT
        )

        startCursor(db, columns, updatedList)

        return updatedList
    }

    private fun startCursor(
        db: SQLiteDatabase,
        columns: Array<String>,
        updatedList: ArrayList<Word>
    ) {
        val cursor =
            db.query(WordEntry.TABLE_NAME, columns, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val itemText =
                    getString(getColumnIndexOrThrow(WordEntry.COLUMN_Word_TEXT))
                val itemCount =
                    getString(getColumnIndexOrThrow(WordEntry.COLUMN_WORD_COUNT)).toInt()
                updatedList.add(Word(itemId, itemText, itemCount))
            }
        }
        cursor.close()
    }

    override fun insertAllWords(words: List<Word>) {
        val db = wordDbHelper.writableDatabase
        try {
            db.apply {
                beginTransaction()
                val values = ContentValues()
                words.forEach { word ->
                    values.put(WordEntry.COLUMN_Word_TEXT, word.text)
                    values.put(WordEntry.COLUMN_WORD_COUNT, word.count)
                    insert(WordEntry.TABLE_NAME, null, values)
                }
                db.setTransactionSuccessful()
            }
        } finally {
            db.endTransaction()
        }
    }

    override fun clearAll() {
        wordDbHelper.writableDatabase.apply {
            delete(WordEntry.TABLE_NAME, null, null)
            close()
        }
    }

    override fun closeDB() {
        wordDbHelper.close()
    }

}