package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class schedule : AppCompatActivity() {

    lateinit var calendar: Calendar
    lateinit var day: SimpleDateFormat
    lateinit var month: SimpleDateFormat
    lateinit var year: SimpleDateFormat


    private lateinit var tvDatePicker: EditText
    private lateinit var btnDatePicker: Button
    private lateinit var tvTime: EditText
    private lateinit var btnTimePicker: Button
    private lateinit var btnschedule: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        tvDatePicker = findViewById(R.id.dateTv)
        btnDatePicker = findViewById(R.id.pickdate_btn)
        tvTime = findViewById(R.id.timeTv)
        btnTimePicker = findViewById(R.id.picktime_btn)
        val mname = findViewById<EditText>(R.id.create_Meeting_name)

        var day_validate1: Int = 0
        var month_validate1: Int = 0
        var year_validate1: Int = 0

        var day_validate: Int
        var month_validate: Int
        var year_validate: Int
        var validate_date_apple:Boolean =true

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)

            day_validate1 = myCalendar.get(Calendar.DAY_OF_MONTH).toString().toInt()
            month_validate1 = myCalendar.get(Calendar.MONTH).toString().toInt()
            year_validate1 = myCalendar.get(Calendar.YEAR).toString().toInt()
            updateLabel(myCalendar)
        }




        btnDatePicker.setOnClickListener() {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()


        }

        btnTimePicker.setOnClickListener() {

            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinutes = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minutes ->
                tvTime.setText("$hourOfDay:$minutes")


            }, startHour, startMinutes, false).show()

        }

        btnschedule = findViewById(R.id.schedule_validate)

        btnschedule.setOnClickListener() {

            calendar = Calendar.getInstance()
            day = SimpleDateFormat("dd")
            month = SimpleDateFormat("MM")
            year = SimpleDateFormat("yyyy")

            day_validate = day.format(calendar.time).toString().toInt()
            month_validate = month.format(calendar.time).toString().toInt()
            year_validate = year.format(calendar.time).toString().toInt()


            if (TextUtils.isEmpty(mname.text.toString())) {
                mname.setError("Plase Input")
                validate_date_apple=false
            }

            if (tvTime.text.toString() == "Time") {
                tvTime.setError("Plase Input")
                validate_date_apple=false
            }

            if (TextUtils.isEmpty(tvDatePicker.text.toString())) {
                tvDatePicker.setError("Plase input")
                validate_date_apple=false
            } else {
                if (day_validate > day_validate1 && month_validate > month_validate1) {
                    tvDatePicker.setError("Wrong date")
                    validate_date_apple=false

                } else if (month_validate > month_validate1 && year_validate > year_validate1) {
                    tvDatePicker.setError("Wrong date")
                    validate_date_apple=false
                } else if (year_validate > year_validate1) {
                    tvDatePicker.setError("Wrong date")
                    validate_date_apple=false
                }
            }

            if(!TextUtils.isEmpty(tvDatePicker.text.toString())&&validate_date_apple==true&&!TextUtils.isEmpty(mname.text.toString())){

                val intent = Intent(Intent.ACTION_INSERT).apply {
                    data = CalendarContract.Events.CONTENT_URI
                    putExtra(CalendarContract.Events.TITLE, "Meeting")
                    putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, tvDatePicker.text.toString())

                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }else{
                    Toast.makeText(
                        getApplicationContext(),
                        "There is no app that can support this action",
                        Toast.LENGTH_SHORT
                    ).show();
                }


            }


        }


    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))
    }
}