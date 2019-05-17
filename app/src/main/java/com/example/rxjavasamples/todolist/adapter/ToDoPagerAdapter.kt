package com.example.androidtutz.todolistapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.example.androidtutz.todolistapp.TodolistFragment


/**
 * Created by K. A. ANUSHKA MADUSANKA on 4/12/2017.
 */

class ToDoPagerAdapter(fm: androidx.fragment.app.FragmentManager, internal var mNumOfTabs: Int) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
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
