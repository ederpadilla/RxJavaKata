package com.example.androidtutz.todolistapp.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

import com.example.androidtutz.todolistapp.ToDoMainActivity
import com.example.androidtutz.todolistapp.TodolistFragment
import com.example.androidtutz.todolistapp.data.ToDoListItem

import java.util.ArrayList

import android.content.Context.MODE_PRIVATE
import com.example.rxjavasamples.R

/**
 * Created by K. A. ANUSHKA MADUSANKA on 12/10/2017.
 */

class ToDoListAdapter(private val todolist: List<ToDoListItem>, private val type: Int, fragment: Fragment) : RecyclerView.Adapter<ToDoListAdapter.MyViewHolder>(), Filterable {
    private var todolistFiltered: List<ToDoListItem>? = null
    private var goal_category_id: Int = 0
    var todoFragment: TodolistFragment


    init {
        this.todolistFiltered = todolist
        this.todoFragment = fragment as TodolistFragment


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapter.MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_goallist_list_item, parent, false)
        val prefs = parent.context.getSharedPreferences("BackTarget", MODE_PRIVATE)
        goal_category_id = prefs.getInt("goal_category_id", 1)//"No name defined" is the default value.

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoListAdapter.MyViewHolder, position: Int) {

        val item = todolistFiltered!![position]
        holder.name.text = item.toDoListItemName


        holder.date.text = item.toDoListItemDescription


        if (item.toDoListItemBackgroundColor != null) {

            if (Integer.parseInt(item.toDoListItemBackgroundColor!!) == 1) {


                holder.buttonsLayout.visibility = View.VISIBLE


            } else {

                holder.buttonsLayout.visibility = View.INVISIBLE

            }

        } else {


        }


    }

    override fun getItemCount(): Int {
        return todolistFiltered!!.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    todolistFiltered = todolist
                } else {
                    val filteredList = ArrayList<ToDoListItem>()
                    for (item in todolist) {

                        if (item.toDoListItemName!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(item)
                        }
                    }

                    todolistFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = todolistFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                todolistFiltered = filterResults.values as ArrayList<ToDoListItem>
                notifyDataSetChanged()
            }
        }
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var name: TextView
        var date: TextView
        var achievedButton: ImageButton
        var button: ImageButton
        var closeButton: ImageButton
        var deleteButton: ImageButton
        var editButton: ImageButton
        var buttonsLayout: LinearLayout


        init {
            name = view.findViewById(R.id.tvGoallistName)
            date = view.findViewById(R.id.tvGoalListAchieveDate)
            achievedButton = view.findViewById(R.id.button_action_plan)
            achievedButton.setOnClickListener(this)
            name.setOnClickListener(this)
            date.setOnClickListener(this)
            button = view.findViewById(R.id.imageButtonMarkAsDone)
            closeButton = view.findViewById(R.id.imageButtonClose)
            deleteButton = view.findViewById(R.id.imageButtonDelete)
            editButton = view.findViewById(R.id.imageButtonEdit)
            buttonsLayout = view.findViewById(R.id.tasklistbuttons)
            button.setOnClickListener(this)
            closeButton.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
            editButton.setOnClickListener(this)
            name.setOnClickListener(this)


        }


        override fun onClick(v: View) {

            if (v === button) {


                todoFragment.markAsAchieved(adapterPosition)


            } else if (v === deleteButton) {


                todoFragment.deleteRow(adapterPosition)


            } else if (v === closeButton) {


                todoFragment.closeButtonsRow(adapterPosition)


            } else if (v === editButton) {


                todoFragment.viewUpdateFragment(adapterPosition)


            } else {


                todoFragment.viewListWithButtons(adapterPosition)


            }

        }
    }


}
