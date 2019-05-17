package com.example.androidtutz.todolistapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.androidtutz.todolistapp.adapter.ToDoListAdapter
import com.example.androidtutz.todolistapp.adapter.RecyclerTouchListener
import com.example.androidtutz.todolistapp.data.ToDoListItem
import com.example.androidtutz.todolistapp.data.ToDoDataManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

import android.content.Context.MODE_PRIVATE
import com.example.rxjavasamples.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TodolistFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TodolistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodolistFragment : androidx.fragment.app.Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    internal var recyclerViewAchievingGoals: androidx.recyclerview.widget.RecyclerView? = null
    private lateinit var fabAddNewToDo: FloatingActionButton
    private var taskStatus = ""
    private var dateFront = ""
    private var toDoDataManager: ToDoDataManager? = null

    private val goalsList = ArrayList<ToDoListItem>()
    private var goalAdapter: ToDoListAdapter? = null
    private var searchEditText: TextView? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }

        toDoDataManager = ToDoDataManager(context!!)
        toDoDataManager!!.open()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_todolist, container, false)
        fabAddNewToDo = view.findViewById(R.id.fab_addNewToDo)
        searchEditText = view.findViewById(R.id.todo_search)

        recyclerViewAchievingGoals = view.findViewById(R.id.recycler_view_achieving_goals)


        if (Integer.parseInt(mParam1!!) == 0) {


            taskStatus = "Achieving"
            dateFront = "Will be achieved by"

        } else {


            taskStatus = "Achieved"
            dateFront = "Successfully achieved by"
        }


        //set fab
        fabAddNewToDo.setImageResource(R.drawable.ic_add_white)
        fabAddNewToDo.setOnClickListener {
            if (Integer.parseInt(mParam1!!) == 0) {

                val add_new_intent = Intent(context, AddToDoActivity::class.java)
                add_new_intent.putExtra("status", "Achieving")
                startActivity(add_new_intent)


            } else {

                val add_new_intent = Intent(context, AddToDoActivity::class.java)
                add_new_intent.putExtra("status", "Achieved")
                startActivity(add_new_intent)

            }
        }
        setRecyclerView()
        loadData()
        addSearchListener()
        return view

    }

    private fun addSearchListener() {
        compositeDisposable.add(
                RxTextView.textChangeEvents(searchEditText!!).skipInitialValue()
                        .debounce(300, TimeUnit.MICROSECONDS)
                        .distinctUntilChanged()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                                goalAdapter!!.getFilter().filter(textViewTextChangeEvent.text())
                            }

                            override fun onError(e: Throwable) {

                            }

                            override fun onComplete() {

                            }
                        })
        )
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    private fun setRecyclerView() {


        goalAdapter = ToDoListAdapter(goalsList, 0, this@TodolistFragment)

        recyclerViewAchievingGoals!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerViewAchievingGoals!!.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerViewAchievingGoals!!.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity!!,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
            )
        )

        recyclerViewAchievingGoals!!.addOnItemTouchListener(RecyclerTouchListener(activity!!,
                recyclerViewAchievingGoals!!, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {

                val goal = goalsList[position]
                val editor = activity!!.getSharedPreferences("goal_related_data", MODE_PRIVATE).edit()
                editor.putString("goal_id", Integer.toString(goal.toDoListItemId))
                editor.putString("status", "0")
                editor.commit()

            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))

        recyclerViewAchievingGoals!!.adapter = goalAdapter


    }

    private fun loadData() {
        val observable = Observable
                .fromArray<ToDoListItem>(*toDoDataManager!!
                        .allToDoListItem_list
                        .toTypedArray<ToDoListItem>())
        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<ToDoListItem>() {
                            override fun onNext(toDoListItem: ToDoListItem) {
                                goalsList.add(toDoListItem)
                            }

                            override fun onError(e: Throwable) {

                            }

                            override fun onComplete() {
                                goalAdapter!!.notifyDataSetChanged()
                            }
                        })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun markAsAchieved(position: Int) {


        if (Integer.parseInt(mParam1!!) == 0) {


            val goal = goalsList[position]
            goal.toDoListItemStatus = "Achieved"
            toDoDataManager!!.updateToDoListItem(goal)
            goalsList.removeAt(position)

            goalAdapter!!.notifyDataSetChanged()

            Toast.makeText(activity, "Marked as achieved", Toast.LENGTH_LONG).show()

        } else {


            val goal = goalsList[position]
            goal.toDoListItemStatus = "Achieving"
            toDoDataManager!!.updateToDoListItem(goal)
            goalsList.removeAt(position)

            goalAdapter!!.notifyDataSetChanged()

            Toast.makeText(activity, "Marked as not achieved", Toast.LENGTH_LONG).show()
        }


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {


            if (recyclerViewAchievingGoals != null) {
                goalsList.clear()
                setRecyclerView()
                loadData()

            }

        }
    }

    fun viewListWithButtons(position: Int) {


        val goal = goalsList[position]
        goal.toDoListItemBackgroundColor = "1"
        goalAdapter!!.notifyItemChanged(position)


    }

    fun closeButtonsRow(position: Int) {


        val goal = goalsList[position]
        goal.toDoListItemBackgroundColor = "0"
        goalAdapter!!.notifyItemChanged(position)


    }

    fun deleteRow(position: Int) {

        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_demo)
        dialog.setTitle("")

        val dialogButtonCancel = dialog.findViewById<View>(R.id.dialog_cancel) as Button
        val dialogButtonOk = dialog.findViewById<View>(R.id.dialog_ok) as Button
        dialogButtonOk.text = "Delete!"

        val dialogDesc = dialog.findViewById<View>(R.id.dialog_message) as TextView

        dialogDesc.text = "Are you sure?"

        dialogButtonCancel.setOnClickListener { dialog.dismiss() }

        dialogButtonOk.setOnClickListener {
            val goal = goalsList[position]
            toDoDataManager!!.deleteToDoListItem(goal)
            goalsList.removeAt(position)
            dialog.dismiss()
            goalAdapter!!.notifyDataSetChanged()
            Toast.makeText(activity, "Deleted successfully", Toast.LENGTH_LONG).show()
        }

        dialog.show()

    }

    fun viewUpdateFragment(position: Int) {

        val goal = goalsList[position]
        val content = goal.toDoListItemName

        val oFragmentManager = activity!!.supportFragmentManager
        val updateTasksFragment = UpdateToDoFragment.newInstance(content!!, 0, this@TodolistFragment, goal)
        updateTasksFragment.show(oFragmentManager, "Sample Fragment")

    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodolistFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): TodolistFragment {
            val fragment = TodolistFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}// Required empty public constructor
