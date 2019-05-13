package com.example.androidtutz.todolistapp.data

/**
 * Created by K. A. ANUSHKA MADUSANKA on 12/12/2017.
 */

class ToDoListItem {


    var toDoListItemId: Int = 0

    var goalId: Int = 0
    var toDoListItemName: String? = null
    var toDoListItemDescription: String? = null
    var toDoListItemBackgroundColor: String? = null
    var toDoListItemMoreInfo: String? = null
    var toDoListItemCreatedDate: String? = null
    var toDoListItemLatUpdatedDate: String? = null
    var toDoListItemPlanedAchievDate: String? = null
    var toDoListItemAchievedDate: String? = null
    var toDoListItemStatus: String? = null
    var userId: String? = null

    constructor() {

    }

    constructor(toDoListItemName: String, toDoListItemDescription: String, toDoListItemCreatedDate: String, toDoListItemLatUpdatedDate: String, toDoListItemStatus: String, toDoListItemPlanedAchievDate: String, goalId: Int) {
        this.toDoListItemName = toDoListItemName
        this.toDoListItemDescription = toDoListItemDescription
        this.toDoListItemCreatedDate = toDoListItemCreatedDate
        this.toDoListItemLatUpdatedDate = toDoListItemLatUpdatedDate
        this.toDoListItemPlanedAchievDate = toDoListItemPlanedAchievDate

        this.toDoListItemStatus = toDoListItemStatus
        this.goalId = goalId
    }

    constructor(toDoListItemId: Int, toDoListItemName: String, toDoListItemDescription: String, toDoListItemCreatedDate: String, toDoListItemPlanedAchievDate: String, toDoListItemStatus: String, toDoListItemLatUpdatedDate: String, goalId: Int) {
        this.toDoListItemId = toDoListItemId
        this.toDoListItemName = toDoListItemName
        this.toDoListItemDescription = toDoListItemDescription
        this.toDoListItemCreatedDate = toDoListItemCreatedDate
        this.toDoListItemLatUpdatedDate = toDoListItemLatUpdatedDate
        this.toDoListItemPlanedAchievDate = toDoListItemPlanedAchievDate
        this.toDoListItemStatus = toDoListItemStatus
        this.goalId = goalId
    }
}
