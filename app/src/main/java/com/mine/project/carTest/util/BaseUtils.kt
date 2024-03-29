package com.mine.project.carTest.util

import android.content.Context
import android.util.TypedValue

object BaseUtils {
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return pxVal
     */
    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.resources.displayMetrics
        ).toInt()
    }
}