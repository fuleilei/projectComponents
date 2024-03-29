package com.fl.ground

import android.content.Context
import android.util.TypedValue

object GroundUtils {
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