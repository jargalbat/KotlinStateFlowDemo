package com.example.kotlinstateflowdemo

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebView
import androidx.annotation.RequiresApi
import java.net.HttpURLConnection
import java.net.URL

class ExtendedWebView : WebView {

    companion object {
        private val JAVASCRIPT_OBJ = "javascript_obj"
        var scroll = false
        var swiper = false
    }

    private var disallowIntercept = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

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
//                if (isSwiperCheck()) {
                Log.w("jagaatest", "isSwiperCheck ${isSwiperCheck()}")
                Log.w("jagaatest", "canScrollHor ${canScrollHor()}")

                if(isSwiperCheck() && canScrollHor()) {
//                    ((MainActivity)getActivity())


                }
                // todo
//                    EventBus.getDefault().post(CustomEvent(CustomEvents.isSwiper, 1, canScrollHor()))
//                }
            }
        }

        return super.onTouchEvent(event)
    }

    private fun canScrollHor(): Boolean {
        evaluateJavascript("javascript:if (typeof hasscroll === \"function\") { hasscroll();}") { s: String ->
            scroll = s == "true"
        }
        return scroll
    }

    private fun isSwiperCheck(): Boolean {
        evaluateJavascript("javascript:if (\$(\".swiper-container-horizontal\").length === 0) {false;}else{true;} ") { value: String ->
            swiper = value == "true"
        }
        return swiper
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