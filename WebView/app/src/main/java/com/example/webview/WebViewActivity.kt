package com.example.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webview.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    private lateinit var url: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        url = intent.getStringExtra(Constants.EXTERNAL_URL).toString()
        initUI()
        loadPage()
    }

    private fun initUI() {
        binding.apply() {
            webview.webViewClient = WebViewClient()
            webview.settings.javaScriptEnabled = true
            webview.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    if (newProgress == 100) {
                        progressBar.visibility=View.GONE
                    }
                    else{
                        progressBar.visibility=View.VISIBLE
                        progressBar.progress=newProgress
                    }
                }
            }
            progressBar.setMax(100)
        }
    }
    private fun loadPage(){
        binding.webview.loadUrl(url)
    }
}

