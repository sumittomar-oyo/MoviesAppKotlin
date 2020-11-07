package com.example.movieappkotlin.utilities

import android.app.Activity
import androidx.viewpager.widget.ViewPager
import java.util.*

class SliderTimer(var context: Activity, var viewPager: ViewPager, var size: Int) : TimerTask() {
    override fun run() {
        context.runOnUiThread {
            if (viewPager.currentItem < size - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                viewPager.currentItem = 0
            }
        }
    }
}
