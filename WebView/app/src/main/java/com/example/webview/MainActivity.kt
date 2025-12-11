package com.example.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        }
    private fun initUI(){
        binding.launchButton.setOnClickListener {
            val externalUrl = "https://nus.edu.sg"
            launchExternalPage(externalUrl)
        }
    }
    private fun launchExternalPage(externalUrl:String){
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(Constants.EXTERNAL_URL,externalUrl)
        startActivity(intent)
    }
    }
