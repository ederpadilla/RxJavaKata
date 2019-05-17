package com.example.androidtutz.todolistapp

import android.net.Uri
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.androidtutz.todolistapp.adapter.ToDoPagerAdapter
import com.example.rxjavasamples.R

class ToDoMainActivity : AppCompatActivity(), TodolistFragment.OnFragmentInteractionListener, UpdateToDoFragment.OnFragmentInteractionListener {

    //define tabs for to do tasks
    private lateinit var todoTabs: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.title = "My To Do List"

        todoTabs = findViewById(R.id.tab_layout)


        //set images to tabs
        todoTabs.addTab(todoTabs.newTab().setIcon(R.drawable.ic_todo))

        todoTabs.addTab(todoTabs.newTab().setIcon(R.drawable.ic_done))

        todoTabs.tabGravity = TabLayout.GRAVITY_FILL

        //set fragments for tabs

        val viewPager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.todo_pager)


        val adapter = ToDoPagerAdapter(supportFragmentManager, todoTabs.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(todoTabs))
        todoTabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
