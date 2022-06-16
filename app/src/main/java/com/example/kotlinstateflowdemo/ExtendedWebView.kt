package com.example.kotlinstateflowdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStore

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

//                if (isSwiperCheck()) {
                    Log.w("jagaatest", "isSwiperCheck ${isSwiperCheck()}")
                    Log.w("jagaatest", "canScrollHor ${canScrollHor()}")

                    if (isSwiperCheck() && canScrollHor()) {
//                    val magazineViewModel =  ViewModelStore(this).get(MagazineViewModel::class.java)
//                        var parentContext = context.applicationContext as MagazineActivity
//                        var parentContext = activity
//                        (MagazineActivity) getActivity()
//                        parentContext.setUserInputEnabled(true)
//                    viewModel.setCookie(JsonData());
//                    parentContext.viewModel.setCookie
//                    var parentContext = parentActivity.this;
//                    ((MainActivity)getActivity())
//                    isUserInputEnabled = false
                    } else {
//                    isUserInputEnabled = true
                    }
//                    EventBus.getDefault().post(CustomEvent(CustomEvents.isSwiper, 1, canScrollHor()))
//                }
                } catch (e: Exception) {
                    Log.w("jagaatest", e.toString())
                }
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

//    fun setActivity(a: AppCompatActivity) {
//        activity = a
//    }

    override fun getContentHeight(): Int {
        return computeVerticalScrollRange()
    }


}