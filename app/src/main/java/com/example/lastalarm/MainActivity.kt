package com.example.lastalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val now = Calendar.getInstance()

        pickerdate!!.init(
            now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH],
            null
        )
        pickertime!!.currentHour = now[Calendar.HOUR_OF_DAY]
        pickertime!!.currentMinute = now[Calendar.MINUTE]

        buttonSetAlarm.setOnClickListener {
            val calendar = Calendar.getInstance()
            val current = Calendar.getInstance()

            if (Build.VERSION.SDK_INT >= 23) {
                calendar[
                        pickerdate!!.year,
                        pickerdate!!.month,
                        pickerdate!!.dayOfMonth,
                        pickertime!!.hour,
                        pickertime!!.minute] =
                        0
            }
            if (calendar <= current) {
                Toast.makeText(applicationContext, "Invalid Date/Time", Toast.LENGTH_LONG).show()
            } else {
                setAlarm(calendar.timeInMillis)
            }
        }

    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager =
                getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
                AlarmManager.RTC,
                timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
        Toast.makeText(this, "ALARM IS SET", Toast.LENGTH_SHORT).show()

    }
}