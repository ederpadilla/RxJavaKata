package com.example.androidtutz.todolistapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.androidtutz.todolistapp.data.ToDoDataManager.Companion.KEY_ToDoListItem_LIST_NAMES_LIST_ID
import com.example.androidtutz.todolistapp.data.ToDoDataManager.Companion.TABLE_ToDoListItem_LIST

import java.util.ArrayList
import java.util.Calendar
import java.util.Collections
import java.util.Comparator

/**
 * Created by K. A. ANUSHKA MADUSANKA on 12/12/2017.
 */

class ToDoDataManager(private val context: Context) {


    private var databaseHelper: DatabaseHandler? = null

    private var database: SQLiteDatabase? = null


    // Getting All ToDoListItem_list
    // Select All Query
    // looping through all rows and adding to list
    // Adding ToDoListItem to list
    //  cursor.close();
    // return ToDoListItem list
    val allToDoListItem_list: ArrayList<ToDoListItem>
        get() {
            val ToDoListItemList = ArrayList<ToDoListItem>()
            val selectQuery = "SELECT  * FROM $TABLE_ToDoListItem_LIST"


            val cursor = database!!.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val ToDoListItem = ToDoListItem()
                    ToDoListItem.toDoListItemId = Integer.parseInt(cursor.getString(0))
                    ToDoListItem.toDoListItemName = cursor.getString(1)
                    ToDoListItem.toDoListItemMoreInfo = cursor.getString(2)
                    ToDoListItem.toDoListItemBackgroundColor = cursor.getString(3)
                    ToDoListItem.toDoListItemMoreInfo = cursor.getString(4)
                    ToDoListItem.toDoListItemCreatedDate = cursor.getString(5)
                    ToDoListItem.toDoListItemLatUpdatedDate = cursor.getString(6)
                    ToDoListItem.toDoListItemPlanedAchievDate = cursor.getString(7)
                    ToDoListItem.toDoListItemAchievedDate = cursor.getString(8)
                    ToDoListItem.toDoListItemStatus = cursor.getString(9)
                    ToDoListItem.goalId = Integer.parseInt(cursor.getString(10))
                    ToDoListItemList.add(ToDoListItem)
                } while (cursor.moveToNext())
            }
            return sortByDate(ToDoListItemList)
        }

    @Throws(SQLException::class)
    fun open(): ToDoDataManager {
        databaseHelper = DatabaseHandler(context)
        database = databaseHelper!!.writableDatabase
        return this
    }


    // Adding new ToDoListItem
    fun addToDoListItem(ToDoListItem: ToDoListItem) {


        // Get Current Date
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val lastUpdatedDate = "$mYear/$mMonth/$mDay"


        val values = ContentValues()
        values.put(KEY_ToDoListItem_LIST_NAME, ToDoListItem.toDoListItemName) // ToDoListItem Name
        values.put(KEY_ToDoListItem_LIST_DESCRIPTION, ToDoListItem.toDoListItemDescription) // ToDoListItem Description
        values.put(KEY_ToDoListItem_LIST_CREATED, ToDoListItem.toDoListItemCreatedDate) // ToDoListItem created date
        values.put(KEY_ToDoListItem_LIST_PLANED_ACHIEVEMENT_DATE, ToDoListItem.toDoListItemPlanedAchievDate) // ToDoListItem planned complition date
        values.put(KEY_ToDoListItem_LIST_ITEM_STATUS, ToDoListItem.toDoListItemStatus) // ToDoListItem status
        values.put(KEY_ToDoListItem_LIST_LAST_UPDATED, lastUpdatedDate) // ToDoListItem last updated
        values.put(KEY_ToDo_Id, ToDoListItem.goalId)


        // Inserting Row
        database!!.insert(TABLE_ToDoListItem_LIST, null, values)

        // database.close();

    }


    // Updating single ToDoListItem
    fun updateToDoListItem(ToDoListItem: ToDoListItem): Int {
        // SQLiteDatabase database = this.getWritableDatabase();

        val values = ContentValues()
        values.put(KEY_ToDoListItem_LIST_NAME, ToDoListItem.toDoListItemName)
        values.put(KEY_ToDoListItem_LIST_DESCRIPTION, ToDoListItem.toDoListItemDescription)
        values.put(KEY_ToDoListItem_LIST_PLANED_ACHIEVEMENT_DATE, ToDoListItem.toDoListItemPlanedAchievDate)
        values.put(KEY_ToDoListItem_LIST_LAST_UPDATED, ToDoListItem.toDoListItemLatUpdatedDate)
        values.put(KEY_ToDoListItem_LIST_ITEM_STATUS, ToDoListItem.toDoListItemStatus)

        // updating row

        //  database.close();

        return database!!.update(TABLE_ToDoListItem_LIST, values, "$KEY_ToDoListItem_LIST_NAMES_LIST_ID = ?",
                arrayOf(ToDoListItem.toDoListItemId.toString()))


    }

    // Deleting single ToDoListItem
    fun deleteToDoListItem(ToDoListItem: ToDoListItem) {

        database!!.delete(TABLE_ToDoListItem_LIST, "$KEY_ToDoListItem_LIST_NAMES_LIST_ID = ?",
                arrayOf(ToDoListItem.toDoListItemId.toString()))
        // database.close();
    }


    private fun sortByDate(goalsToSort: ArrayList<ToDoListItem>): ArrayList<ToDoListItem> {


        val sorted_goals_by_date = ArrayList<ToDoListItem>()


        for (goal in goalsToSort) {

            val date = goal.toDoListItemPlanedAchievDate
            if (date != null) {

                sorted_goals_by_date.add(goal)

            }


        }


        for (goal in sorted_goals_by_date) {

            val date = goal.toDoListItemPlanedAchievDate
            if (date != null) {
                val year = date.substring(0, 4)
                val month = date.substring(7, 9)
                val day = date.substring(12, 14)

                val dayMerged = year + month + day
                //     int dayInt = Integer.parseInt(dayMerged);
                goal.toDoListItemMoreInfo = dayMerged

            } else {

            }


        }


        Collections.sort(sorted_goals_by_date) { obj1, obj2 ->
            Integer.valueOf(Integer.parseInt(obj1.toDoListItemMoreInfo!!)).compareTo(Integer.parseInt(obj2.toDoListItemMoreInfo!!)) // To compare integer values
        }
        return sorted_goals_by_date


    }

    companion object {

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