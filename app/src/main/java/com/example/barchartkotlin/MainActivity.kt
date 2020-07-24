package com.example.barchartkotlin

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentDate = Calendar.getInstance()
        val previousDate = Calendar.getInstance()

        currentDate.add(Calendar.DAY_OF_MONTH, 1);
        previousDate.add(Calendar.DAY_OF_MONTH, -1);

        val df = SimpleDateFormat("MMM dd, yyyy")
        var formattedDate = df.format(currentDate.time)

        previousDate.add(Calendar.DATE, 8)
        var formattedDate1 = df.format(previousDate.time)

        date.text = "$formattedDate - $formattedDate1"

        prev_btn.setOnClickListener {
            currentDate.add(Calendar.DATE, -7);
            formattedDate = df.format(currentDate.time)

            previousDate.add(Calendar.DATE, -7)
            formattedDate1 = df.format(previousDate.time)

            date.text = "$formattedDate-$formattedDate1"
        }
        next_btn.setOnClickListener {
            currentDate.add(Calendar.DATE, 7)
            formattedDate = df.format(currentDate.time)
            previousDate.add(Calendar.DATE, 7)
            formattedDate1 = df.format(previousDate.time)
            date.text = "$formattedDate-$formattedDate1"

        }

       graph()
    }

    private fun graph() {
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.setMaxVisibleValueCount(50)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(true)

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(0f, 2.0f))
        entries.add(BarEntry(1f, 5.0f))
        entries.add(BarEntry(2f, 20.0f))
        entries.add(BarEntry(3f, 15.0f))
        entries.add(BarEntry(4f, 13.0f))
        entries.add(BarEntry(5f, 15.0f))
        entries.add(BarEntry(6f, 8.0f))


        val set = BarDataSet(entries, "Data set")
        set.setColors(Color.parseColor("#6FCF97"))

        val data = BarData(set)
        data.barWidth = 0.9f
        chart.setData(data)

        chart.axisRight.isEnabled=false

        val des: Description = chart.description
        des.isEnabled = false


        val months = arrayOf("S", "M", "T", "W", "T", "F", "S")
        val axis = chart.xAxis
        axis.valueFormatter = IndexAxisValueFormatter(months)
        // axis.setDrawGridLines(false)

        axis.position=XAxis.XAxisPosition.BOTTOM


    }
}