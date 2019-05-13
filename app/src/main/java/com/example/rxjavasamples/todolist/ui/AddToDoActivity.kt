package com.example.androidtutz.todolistapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.TextView

import com.example.androidtutz.todolistapp.adapter.MyDialog
import com.example.androidtutz.todolistapp.data.ToDoListItem
import com.example.androidtutz.todolistapp.data.ToDoDataManager
import com.example.rxjavasamples.R
import com.rengwuxian.materialedittext.MaterialEditText

import java.text.SimpleDateFormat
import java.util.Calendar

class AddToDoActivity : AppCompatActivity() {

    private var date: String? = null
    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var currentDate: String? = null
    private lateinit var calendar: Calendar
    private lateinit var mdformat: SimpleDateFormat
    private var goalStatus: String? = null
    private var closeImageButton: ImageButton? = null
    private var clearImageButton: ImageButton? = null
    private var setTimeImageButton: ImageButton? = null
    var addNotificationImageButton: ImageButton? = null
    private var saveImageButton: ImageButton? = null

    private var goalContent: MaterialEditText? = null
    private var rangeTextView: TextView? = null
    lateinit var reminderTextView: TextView
    var calendarForReminder: Calendar? = null

    private var horizontalView: View? = null
    lateinit var horizontalViewReminder: View

    // private RemindersDataManager remindersDataManager;
    private val rounded: Long = 0
    private val goalCategoryId: Int = 0

    private var dbManager: ToDoDataManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_add_goal)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        goalStatus = intent.getStringExtra("status")

        dbManager = ToDoDataManager(this)
        dbManager!!.open()

        calendar = Calendar.getInstance()
        mdformat = SimpleDateFormat("yyyy / MM / dd ")
        currentDate = mdformat.format(calendar.time)


        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)
        val correctMonthint = mMonth + 1

        //set date to the right format
        if (correctMonthint < 10 && mDay < 10) {

            date = "$mYear / 0$correctMonthint / 0$mDay"

        } else if (correctMonthint < 10 && mDay >= 10) {

            date = "$mYear / 0$correctMonthint / $mDay"

        } else if (correctMonthint >= 10 && mDay < 10) {

            date = "$mYear / $correctMonthint / 0$mDay"


        } else {

            date = "$mYear / $correctMonthint / $mDay"
        }


        rangeTextView = findViewById<View>(R.id.tvRange) as TextView
        reminderTextView = findViewById<View>(R.id.tvReminder) as TextView

        horizontalView = findViewById(R.id.view_horizontal_line) as View
        horizontalViewReminder = findViewById(R.id.view_horizontal_line_reminder) as View

        closeImageButton = findViewById<View>(R.id.imageButtonClose) as ImageButton

        closeImageButton!!.setOnClickListener {
            val intent = Intent(this@AddToDoActivity, ToDoMainActivity::class.java)

            startActivity(intent)
        }


        goalContent = findViewById<View>(R.id.etTask) as MaterialEditText


        clearImageButton = findViewById<View>(R.id.imageButtonClear) as ImageButton

        clearImageButton!!.setOnClickListener { goalContent!!.setText(" ") }


        setTimeImageButton = findViewById<View>(R.id.imageButtonTimeRange) as ImageButton

        setTimeImageButton!!.setOnClickListener { viewDatePickerDialog() }


        saveImageButton = findViewById<View>(R.id.imageButtonSave) as ImageButton

        saveImageButton!!.setOnClickListener {
            if (goalContent!!.text!!.toString() == "") {


                MyDialog().openConfirmDialog(this@AddToDoActivity, "Empty field", "Please, add a content")


            }
            if (false) {


                MyDialog().openConfirmDialog(this@AddToDoActivity, "Empty field", "Please, set the time")


            } else {


                if (calendarForReminder != null) {

                    setAlarm(calendarForReminder)


                }

                dbManager!!.addToDoListItem(ToDoListItem(
                        goalContent!!.text!!.toString(),
                        "desc",
                        currentDate.toString(),
                        currentDate.toString(),
                        goalStatus.toString(),
                        date.toString(),
                        goalCategoryId))

                val main = Intent(this@AddToDoActivity, ToDoMainActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                main.putExtra("result", "1")
                //dialog.dismiss();
                startActivity(main)

            }
        }


    }


    private fun setAlarm(targetCal: Calendar?) {


    }


    private fun viewDatePickerDialog() {

        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // targetedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    mYear = year


                    mMonth = monthOfYear + 1

                    mDay = dayOfMonth

                    if (mMonth < 10 && mDay < 10) {

                        date = "$mYear / 0$mMonth / 0$mDay"

                    } else if (mMonth < 10 && mDay >= 10) {

                        date = "$mYear / 0$mMonth / $mDay"

                    } else if (mMonth >= 10 && mDay < 10) {

                        date = "$mYear / $mMonth / 0$mDay"


                    } else {

                        date = "$mYear / $mMonth / $mDay"
                    }

                    Log.i("datebug", "came here$date  year  $mYear month  $mMonth day  $mDay")

                    rangeTextView!!.text = date
                    setTimeImageButton!!.setImageDrawable(resources.getDrawable(R.drawable.ic_clock))
                    saveImageButton!!.setImageDrawable(resources.getDrawable(R.drawable.ic_save))
                    horizontalView!!.visibility = View.VISIBLE
                }, mYear, mMonth, mDay)
        datePickerDialog.show()


    }

}
