package com.maden.customdatepicker

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi

object DateUtil {
    // 1 enabled, 0 disabled
    private val arrayNumber = arrayListOf<Int>(
        0, //Monday
        1, //Tuesday
        1, //Wednesday
        0, //Thursday
        0, //Friday
        1, //Saturday
        0  //Sunday
    )

    private const val isDisableNumber = 0

    private const val calculateTimeL = 24 * 60 * 60 * 1000
    fun calculateTime(time: Long): Long = time + calculateTimeL

    fun isDisable(currentDay: Int): Boolean {
        return arrayNumber[currentDay - 1] == isDisableNumber
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun isDisableStatic(currentDay: Int): Boolean {
        return (currentDay == Calendar.SUNDAY || currentDay == Calendar.SATURDAY)
    }
}