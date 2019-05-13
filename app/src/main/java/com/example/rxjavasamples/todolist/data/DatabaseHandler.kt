package com.example.androidtutz.todolistapp.data

/**
 * Created by K . A. Anushka Madusanka on 12/12/2017.
 */

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {


        //Create ToDoListItem_list table
        val CREATE_ToDoS_LIST_ITEMS_TABLE = ("CREATE TABLE " + TABLE_ToDoListItem_LIST + "("
                + KEY_ToDoListItem_LIST_NAMES_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ToDoListItem_LIST_NAME + " TEXT,"
                + KEY_ToDoListItem_LIST_DESCRIPTION + " TEXT,"
                + KEY_ToDoListItem_LIST_COLOR + " TEXT,"
                + KEY_ToDoListItem_LIST_MORE_INFO + " TEXT,"
                + KEY_ToDoListItem_LIST_CREATED + " TEXT,"
                + KEY_ToDoListItem_LIST_LAST_UPDATED + " TEXT,"
                + KEY_ToDoListItem_LIST_PLANED_ACHIEVEMENT_DATE + " TEXT,"
                + KEY_ToDoListItem_LIST_ACHIEVED_DATE + " TEXT,"
                + KEY_ToDoListItem_LIST_ITEM_STATUS + " TEXT,"
                + KEY_ToDo_Id + " INTEGER" + ")")
        db.execSQL(CREATE_ToDoS_LIST_ITEMS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS $TABLE_ToDoListItem_LIST")

        // Create tables again
        onCreate(db)

    }

    companion object {


        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "db_todo"


        // ToDoListItem_list table name
        private val TABLE_ToDoListItem_LIST = "ToDoListItem_list"


        // ToDoListItem_list Table Columns names
        private val KEY_ToDoListItem_LIST_NAMES_LIST_ID = "ToDoListItem_list_id"
        private val KEY_ToDoListItem_LIST_NAME = "ToDoListItem_list_name"
        private val KEY_ToDoListItem_LIST_DESCRIPTION = "ToDoListItem_list_description"
        private val KEY_ToDoListItem_LIST_COLOR = "ToDoListItem_list_background_color"
        private val KEY_ToDoListItem_LIST_MORE_INFO = "ToDoListItem_list_more_info"
        private val KEY_ToDoListItem_LIST_CREATED = "ToDoListItem_list_created_date"
        private val KEY_ToDoListItem_LIST_LAST_UPDATED = "ToDoListItem_list_last_updated_date"
        private val KEY_ToDoListItem_LIST_PLANED_ACHIEVEMENT_DATE = "ToDoListItem_list_planed_achievement_date"
        private val KEY_ToDoListItem_LIST_ACHIEVED_DATE = "ToDoListItem_list_achieved_date"
        private val KEY_ToDoListItem_LIST_ITEM_STATUS = "ToDoListItem_list_item_status"
        private val KEY_ToDo_Id = "ToDo_id"
    }


}

