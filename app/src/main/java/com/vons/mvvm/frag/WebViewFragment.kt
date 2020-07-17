package com.vons.mvvm.frag

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentWebViewBinding
import com.vons.mvvm.entity.JsToJava

class WebViewFragment : BaseFragment() {

    private val args: WebViewFragmentArgs by navArgs()
    private lateinit var webContent: WebView
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        webContent = WebView(mContext)
        initWebView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url = args.url
        val title = args.title
        val dataBinding = FragmentWebViewBinding.inflate(inflater)
        dataBinding.tvTitle.text = title
        dataBinding.ivBack.setOnClickListener {
            if (webContent.canGoBack()) {
                webContent.goBack()
            } else {
                webContent.loadUrl("about:blank")
                findNavController().navigateUp()
            }
        }
        dataBinding.llwebContent.addView(webContent)
        if (url.isNotEmpty()) {
            webContent.loadUrl(url)
        }
        return dataBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        clearWebView()
    }

    private fun clearWebView() {
        webContent.clearCache(true)
        val parent = webContent.parent
        (parent as ViewGroup).removeView(webContent)
        webContent.stopLoading()
        webContent.settings.javaScriptEnabled = false
        webContent.clearHistory()
//        webContent.clearView()
        webContent.removeAllViews()
        webContent.clearFormData()
        webContent.clearFocus()
        webContent.destroy()
    }

    object MWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }

    class MWebClient(val context: Context) : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.let {
                if (it.url.startsWith("jianshu://", false)) {
                    context.startActivity(Intent.parseUri(it.url, Intent.URI_INTENT_SCHEME))
                    return true
                }
                view.loadUrl(it.url)
                return true
            }
            return false
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        webContent.layoutParams = lp
        webContent.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
        webContent.isHorizontalScrollBarEnabled = false // 水平不显示
        webContent.isVerticalScrollBarEnabled = false // 垂直不显示

//        mWvContent.setInitialScale(5);
        //        mWvContent.setInitialScale(5);
        val webSettings: WebSettings = webContent.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.domStorageEnabled = true // 开启DOM

        webSettings.defaultTextEncodingName = "utf-8"
        webSettings.allowFileAccess = true // 支持文件流

        webSettings.setSupportZoom(false)
        webSettings.builtInZoomControls = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        }
        webSettings.useWideViewPort = true // 调整到适合webview大小

        webSettings.loadWithOverviewMode = true // 调整到适合webview大小

//        webSettings.defaultZoom = WebSettings.ZoomDensity.FAR // 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常

//        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webContent.webChromeClient = MWebChromeClient
        webContent.webViewClient = MWebClient(mContext)
        webContent.addJavascriptInterface(JsToJava(), "androidShare")
    }

    fun getWebContent(): WebView {
        return this.webContent
    }
}

