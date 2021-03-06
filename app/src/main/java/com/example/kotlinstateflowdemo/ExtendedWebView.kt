package com.example.kotlinstateflowdemo

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class ExtendedWebView : WebView {
    companion object {
        private val JAVASCRIPT_OBJ = "javascript_obj"
        var scroll = false
        var swiper = false
        val activity: AppCompatActivity? = null
    }

//    var activity: AppCompatActivity? = null

    private var disallowIntercept = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var iCallbackListener: ICallbackListener

    private fun getActivity(): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (event.pointerCount > 1 && !disallowIntercept) {
                    disallowIntercept = true
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP -> {
                disallowIntercept = false
                parent.requestDisallowInterceptTouchEvent(false)
            }

            MotionEvent.ACTION_DOWN -> {
                try {
                    iCallbackListener = getActivity() as ICallbackListener
                    iCallbackListener.setUserInputEnabled(false)
                } catch (e: Exception) {
                    Log.w("jagaatest", e.toString())
                }
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        iCallbackListener = getActivity() as ICallbackListener
        iCallbackListener.setUserInputEnabled(true)
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(ev)
    }

    fun getContentWidth(): Int {
        return computeHorizontalScrollRange()
    }

    override fun getContentHeight(): Int {
        return computeVerticalScrollRange()
    }
}