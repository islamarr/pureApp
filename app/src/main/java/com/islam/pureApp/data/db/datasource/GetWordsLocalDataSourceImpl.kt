package com.islam.pureApp.data.db.datasource

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.islam.pureApp.data.db.WordContract
import com.islam.pureApp.data.db.WordDbHelper
import com.islam.pureApp.domain.entites.Word

class GetWordsLocalDataSourceImpl(context: Context) : GetWordsLocalDataSource {

    private var wordDbHelper: WordDbHelper = WordDbHelper(context)

    override fun getAllWords(): List<Word> {
        val updatedList = ArrayList<Word>()

        val db = wordDbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            WordContract.WordEntry.COLUMN_Word_TEXT,
            WordContract.WordEntry.COLUMN_WORD_COUNT
        )

        val cursor = db.query(
            WordContract.WordEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )


        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val itemName =
                    getString(getColumnIndexOrThrow(WordContract.WordEntry.COLUMN_Word_TEXT))
                val itemTotal =
                    getString(getColumnIndexOrThrow(WordContract.WordEntry.COLUMN_WORD_COUNT))
                updatedList.add(Word(itemId, itemName, itemTotal.toInt()))
            }
        }
        cursor.close()

        return updatedList
    }

    override fun insertAllWords(words: List<Word>) {
        val db = wordDbHelper.writableDatabase

        val values = ContentValues()
        words.forEach {
            values.put(WordContract.WordEntry.COLUMN_Word_TEXT, it.text)
            values.put(WordContract.WordEntry.COLUMN_WORD_COUNT, it.count)
            db?.insert(WordContract.WordEntry.TABLE_NAME, null, values)
        }
    }

    override fun clearAll() {
        val db = wordDbHelper.writableDatabase
        db.delete(WordContract.WordEntry.TABLE_NAME, null, null)
    }
}