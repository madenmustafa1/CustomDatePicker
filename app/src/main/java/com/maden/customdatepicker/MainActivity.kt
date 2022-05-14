package com.maden.customdatepicker


import android.icu.util.GregorianCalendar
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class MainActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nowCalendar: Calendar = Calendar.getInstance()
        var datePickerDialog: DatePickerDialog
        with(nowCalendar) {
            datePickerDialog = DatePickerDialog.newInstance(
                this@MainActivity,
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DAY_OF_MONTH)
            )
        }

        val g1 = GregorianCalendar()
        g1.add(Calendar.DATE, 1)
        val gc = GregorianCalendar()
        gc.add(Calendar.DAY_OF_MONTH, 30)
        val daysList: MutableList<Calendar> = LinkedList<Calendar>()
        val cAux = Calendar.getInstance()

        while (cAux.timeInMillis <= gc.timeInMillis) {
            val dayOfWeek: Int = cAux.get(Calendar.DAY_OF_WEEK)
            var currentDay = dayOfWeek - 1
            if (dayOfWeek == 1) currentDay = 7

            if (DateUtil.isDisable(currentDay)) {
                val disabledDays = arrayOfNulls<Calendar>(1)
                disabledDays[0] = cAux
                datePickerDialog.disabledDays = disabledDays
            } else {
                val c = Calendar.getInstance()
                c.timeInMillis = cAux.timeInMillis
                daysList.add(c)
            }

            cAux.timeInMillis = DateUtil.calculateTime(cAux.timeInMillis)
        }
        val daysArray: Array<Calendar?> = arrayOfNulls(daysList.size)

        for (i in daysArray.indices) {
            daysArray[i] = daysList[i]
        }
        datePickerDialog.selectableDays = daysArray
        datePickerDialog.show(supportFragmentManager, "Datepickerdialog")
    }

    override fun onDateSet(
        view: DatePickerDialog?,
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int) {
        val date =
            "You picked the following date: " +
                    dayOfMonth.toString() +
                    "/" + (monthOfYear + 1).toString() +
                    "/" + year
        println(date)
    }
}

