package com.example.explicitintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var lastQuote:String?=null
    var rlWriteQuote: ActivityResultLauncher<Intent>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpBtns()
        registerForResult()
    }
    protected fun setUpBtns(){
        val ids = intArrayOf(R.id.writeQuote,R.id.showQuote)
        for (i in ids.indices){
            var btn = findViewById<Button>(ids[i])
            btn?.setOnClickListener(this)
        }
    }
    protected fun registerForResult(){
        rlWriteQuote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult -> if(result.resultCode == RESULT_OK)
                lastQuote = result.data?.getStringExtra("newQuote")
        }
    }
    override fun onClick(v:View){
        if(v.id == R.id.writeQuote){
            val intent = Intent(this,WriteQuote::class.java)
            rlWriteQuote?.launch(intent)
        }
        else if(v.id ==R.id.showQuote){
            val intent = Intent(this,ShowQuote::class.java)
            intent.putExtra("quote",lastQuote)
            startActivity(intent)
        }
    }
}