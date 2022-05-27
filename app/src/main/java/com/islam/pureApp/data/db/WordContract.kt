package com.islam.pureApp.data.db

import android.provider.BaseColumns

object WordEntry : BaseColumns {
    const val TABLE_NAME = "words"
    const val COLUMN_Word_TEXT = "text"
    const val COLUMN_WORD_COUNT = "count"
}

const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${WordEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${WordEntry.COLUMN_Word_TEXT} TEXT," +
            "${WordEntry.COLUMN_WORD_COUNT} TEXT)"

const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${WordEntry.TABLE_NAME}"
