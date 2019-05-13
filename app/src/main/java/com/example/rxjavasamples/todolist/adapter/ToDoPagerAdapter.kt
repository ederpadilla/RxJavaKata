package com.example.androidtutz.todolistapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import com.example.androidtutz.todolistapp.TodolistFragment


/**
 * Created by K. A. ANUSHKA MADUSANKA on 4/12/2017.
 */

class ToDoPagerAdapter(fm: FragmentManager, internal var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        //returns to do list views
        when (position) {
            0 -> {

                return TodolistFragment.newInstance("0", "to do tasks")
            }
            1 -> {

                return TodolistFragment.newInstance("1", "done tasks")
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}
