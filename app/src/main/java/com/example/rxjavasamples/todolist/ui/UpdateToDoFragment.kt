package com.example.androidtutz.todolistapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import android.widget.Toast

import com.example.androidtutz.todolistapp.data.ToDoListItem
import com.example.androidtutz.todolistapp.data.ToDoDataManager
import com.example.rxjavasamples.R
import com.rengwuxian.materialedittext.MaterialEditText


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UpdateToDoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UpdateToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateToDoFragment : DialogFragment(), View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var closeImageButton: ImageButton? = null
    private var saveImageButton: ImageButton? = null

    private var materialEditText: MaterialEditText? = null

    private var dbManager: ToDoDataManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update_tasks, container, false)


        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        materialEditText = view.findViewById<View>(R.id.etTask) as MaterialEditText
        materialEditText!!.setText(mParam1)


        dbManager = ToDoDataManager(context!!)
        dbManager!!.open()

        closeImageButton = view.findViewById<View>(R.id.imageButtonClose) as ImageButton

        closeImageButton!!.setOnClickListener { dialog.dismiss() }

        saveImageButton = view.findViewById<View>(R.id.imageButtonSave) as ImageButton

        saveImageButton!!.setOnClickListener {
            val newGoal = materialEditText!!.text!!.toString()

            if (materialEditText!!.text!!.toString() == "") {


            } else {


                goal!!.toDoListItemName = newGoal

                Toast.makeText(activity, "Updated successfully", Toast.LENGTH_LONG).show()
            }


            dbManager!!.updateToDoListItem(goal!!)


            val todolistFragment = parentFragment as TodolistFragment
            todolistFragment.userVisibleHint = true
            dialog.dismiss()
        }


        return view

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
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

    override fun onClick(v: View) {

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

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"


        lateinit var parentFragment: Fragment
        var fragmentTypeOfParent: Int = 0
        private var goal: ToDoListItem? = null
        private var objectCame: Any? = null

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment UpdateToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, fragemtType: Int, fragmentToDeal: Fragment, `object`: Any): UpdateToDoFragment {
            val fragment = UpdateToDoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)

            fragment.arguments = args

            parentFragment = fragmentToDeal
            fragmentTypeOfParent = fragemtType

            objectCame = `object`


            goal = `object` as ToDoListItem


            return fragment
        }
    }
}// Required empty public constructor
