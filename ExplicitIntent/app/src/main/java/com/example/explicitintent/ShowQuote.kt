package com.example.explicitintent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ShowQuote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_quote)
        val intent = getIntent()
        val quote = intent.getStringExtra("quote")
        val textView = findViewById<TextView>(R.id.quote)
        if(textView!=null){
            textView.text = quote
        }
        val ok = findViewById<Button>(R.id.ok)
        ok?.setOnClickListener{
            finish()
        }
    }
}