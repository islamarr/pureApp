package com.islam.pureApp.data.db.datasource

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.islam.pureApp.data.db.WordDbHelper
import com.islam.pureApp.data.db.WordEntry
import com.islam.pureApp.domain.entites.Word

class GetWordsLocalDataSourceImpl(context: Context) : GetWordsLocalDataSource {

    private var wordDbHelper: WordDbHelper = WordDbHelper(context)

    override fun getAllWords(): List<Word> {
        val updatedList = ArrayList<Word>()

        val db = wordDbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            WordEntry.COLUMN_Word_TEXT,
            WordEntry.COLUMN_WORD_COUNT
        )

        val cursor =
            db.query(WordEntry.TABLE_NAME, projection, null, null, null, null, null)

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

        return updatedList
    }

    override fun insertAllWords(words: List<Word>) {
        words.forEach {
            insertWord(it)
        }
    }

    private fun insertWord(word: Word) {
        val values = ContentValues().apply {
            put(WordEntry.COLUMN_Word_TEXT, word.text)
            put(WordEntry.COLUMN_WORD_COUNT, word.count)
        }

        wordDbHelper.writableDatabase.apply {
            insert(WordEntry.TABLE_NAME, null, values)
            close()
        }
    }

    override fun clearAll() {
        wordDbHelper.writableDatabase.apply {
            delete(WordEntry.TABLE_NAME, null, null)
            close()
        }
    }

}