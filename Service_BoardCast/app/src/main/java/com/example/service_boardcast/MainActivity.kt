package com.example.service_boardcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val recv = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "on_rand_play"){
                val musicName = intent.getStringExtra("music_name")
                show_mp3_name(musicName)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val start_btn = findViewById<Button>(R.id.play_btn)
        start_btn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            intent.action = "rand_play"
            startService(intent)
        }
        val stop_btn = findViewById<Button>(R.id.stop_btn)
        stop_btn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
        registerReceiver(recv, IntentFilter("on_rand_play"), RECEIVER_EXPORTED)
    }
        fun show_mp3_name(name:String?){
            if(name!=null){
                val songName = findViewById<TextView>(R.id.music_name)
                songName?.text = name
            }
        }
}