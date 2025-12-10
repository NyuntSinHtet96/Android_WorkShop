package com.example.explicitintent

import android.R.string.ok
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.explicitintent.databinding.ActivityMainBinding

class WriteQuote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_write_quote)

            val ok = findViewById<Button>(R.id.ok)
            ok?.setOnClickListener {

            val newQuote = findViewById<EditText>(R.id.newQuote)
                if(newQuote != null){

            val intent = Intent()
            val quote = newQuote.text.toString()
            intent.putExtra("newQuote", quote)
            setResult(RESULT_OK, intent)
            finish()
            }
            }
        }
    }