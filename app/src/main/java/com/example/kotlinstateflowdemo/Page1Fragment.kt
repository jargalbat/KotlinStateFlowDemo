package com.example.kotlinstateflowdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings

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

        // Cookie
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)
        cookieManager.removeAllCookies(null)
        cookieManager.setCookie(
            "https://d1jo9x45ta5zr4.cloudfront.net",
            "CloudFront-Policy="
                    + "eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9kMWpvOXg0NXRhNXpyNC5jbG91ZGZyb250Lm5ldC9jb250ZW50cy8xMjI5LzUvZGVmYXVsdC8qIiwiQ29uZGl0aW9uIjp7IkRhdGVMZXNzVGhhbiI6eyJBV1M6RXBvY2hUaW1lIjoxNjU1MzU2Mzc0fX19XX0_"
                    + ";"
                    + "path="
                    + "/contents/1229/5/default"
                    + ";"
        )
        cookieManager.setCookie(
            "https://d1jo9x45ta5zr4.cloudfront.net",
            "CloudFront-Signature="
                    + "JloSmYsjtWe8JvhQsKOw3D2a9zP090XnVWDPa4frUHBe5Bcv1Ar6qtSwatOF7HQhB8rT0Q3ldoRl~WIwHuArhWoej5LaRajz~Ka2vMB~1Lk6g-GdK0W7DCVAvY75mE4zus54qw263M4P-rLa2pTRLjFS2Sgqr0D2uXMeHPS1j2A4NQkEsQkvxVguFkaRt4F0pUBKRP4wRC0an0G2b8CyCr9i2nTYBpda4x7DD3MQz9Jc6~kbibStfKZ645~dpsw0LG7keymtkOzY9LtT58QIpokldXoYPfl-WwoyOdgq-ECPUu7Vj9q1m1hlad5CVc3gI7ZT62-nUkWiVXvV8mVztA__"
                    + ";"
                    + "path="
                    + "/contents/1229/5/default"
                    + ";"
        )
        cookieManager.setCookie(
            "https://d1jo9x45ta5zr4.cloudfront.net",
            "CloudFront-Key-Pair-Id="
                    + "APKAJ4X56YZGXJGBPE6A"
                    + ";"
                    + "path="
                    + "/contents/1229/5/default"
                    + ";"
        )
        cookieManager.flush()

        // Web View
//        webView.loadUrl("https://ebook-reader-2b784.web.app/0003/index.html")
        webView.loadUrl("https://d1jo9x45ta5zr4.cloudfront.net/contents/1229/5/default/0021/index.html")
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

        return view
    }

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