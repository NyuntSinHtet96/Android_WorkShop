package com.example.imagedownloads

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.net.URL
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initBtn()
    }
    private fun initBtn(){
        val btn = findViewById<Button>(R.id.fetch)
        btn?.setOnClickListener{
            val url = findViewById<EditText>(R.id.url)?.text.toString()
            val file = createDestFile(url)

        Thread{
            if(downloadImage(url,file)){
                runOnUiThread {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    val imageView = findViewById<ImageView>(R.id.image_view)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }.start()
    }
    }
    private fun downloadImage(url:String, file:File):Boolean{
        try{
            URL(url).openStream().use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
        }
    }
            return true
        } catch (_: Exception) {
            return false
        }
}
    private fun createDestFile(url: String) : File {
        var ext = ""
        val pos = url.lastIndexOf(".")
        if (pos != -1) {
            ext = url.substring(pos)
        }

        val filename = UUID.randomUUID().toString() + ext
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File(dir, filename)
    }
}