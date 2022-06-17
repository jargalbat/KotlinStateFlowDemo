package com.example.kotlinstateflowdemo

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.activity.viewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    private lateinit var iCallbackListener: ICallbackListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_page1, container, false)
        val webView: ExtendedWebView = view.findViewById(R.id.webview)
        webView.getContentWidth()
//        val webView: WebView = view.findViewById(R.id.webview2)

        // Cookie
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        // Web View
//        webView.loadUrl("https://ebook-reader-2b784.web.app/0003/index.html")
        webView.loadUrl("https://d1jo9x45ta5zr4.cloudfront.net/contents/1229/5/default/0021/index.html")
//        webView.loadUrl("https://d1jo9x45ta5zr4.cloudfront.net/contents/1962/1/default/0001/index.html")
//        webView.loadUrl("https://d2dlq340tge5us.cloudfront.net/contents/2391/14/default/0030/index.html")
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.loadWithOverviewMode = true
        webSettings.builtInZoomControls = true
        webSettings.mediaPlaybackRequiresUserGesture = false
        webSettings.defaultTextEncodingName = "utf-8"

//        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

//        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        //FOR WEBPAGE SLOW UI
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }

//        webView.evaluateJavascript("javascript:if (typeof woovoo === \"function\") { woovoo();}", null)

//        iCallbackListener = activity as ICallbackListener
//        val btn1: Button = view.findViewById(R.id.btn1)
//        btn1.setOnClickListener {
//            iCallbackListener.setUserInputEnabled(gg)
//            gg = !gg
//        }

        // Set web view client
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
//                textview.setText("Page Loading Started ...")
            }

            override fun onPageFinished(view: WebView, url: String) {
                webView?.evaluateJavascript(
                    "javascript:if (typeof woovoo === \"function\") { woovoo();}",
                    null
                )
                // Page loading finished
                // Enable disable back forward button
//                textview.setText("Page Loading Finished ....")
            }
        }

        return view
    }


//    private var gg: Boolean = false

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}