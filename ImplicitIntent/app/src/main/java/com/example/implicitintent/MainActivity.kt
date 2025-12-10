package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupBtns()
    }
    protected fun setupBtns(){
        val ids = intArrayOf(
            R.id.visit_nus,
            R.id.locate_nus,
            R.id.call_nus,
            R.id.email_nus
        )
        for (i in 0 until ids.size){
            val btn = findViewById<Button>(ids[i])
            btn?.setOnClickListener(this)
        }
    }
    override fun onClick(v:View){
        var intent: Intent? = null
        var uri: Uri? = null

        val id = v.id

        if(id == R.id.visit_nus){
            uri = Uri.parse("http://www.iss.nus.edu.sg")
            intent = Intent(Intent.ACTION_VIEW,uri)
        }
        else if(id == R.id.locate_nus){
            uri = Uri.parse("geo:1.296643,103.776398")
            intent = Intent(Intent.ACTION_VIEW, uri)
        } else if (id == R.id.call_nus) {
            uri = Uri.parse("tel:(+6565166666")
            intent = Intent(Intent.ACTION_DIAL, uri)
        } else if (id == R.id.email_nus) {
            intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("null@nus.edu.sg"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "For Enquiry")
            intent.putExtra(Intent.EXTRA_TEXT, "Please state your enquiry ...")
        }
        if(intent !=null){
            startActivity(intent)
        }
    }
}