package com.example.appspecificfiles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appspecificfiles.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: FileRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = ExternalFileRepository(this)
        initButtons()
    }
    private fun initButtons() {
        binding.apply {
            saveButton.setOnClickListener {
                val text = noteText.text.toString()
                if (text.isEmpty()) {
                    showToast("Please enter your note")
                } else {
                    saveNote(text)
                }
            }
            retrieveButton.setOnClickListener {
                readNote()
            }
        }
    }
    private fun saveNote(note:String){
        try {
            repository.saveNote(note)
            binding.noteText.text.clear()
            showToast("Note saved")
        }
        catch(e:Exception){
            showToast("Can't write to file")
            e.printStackTrace()
        }
    }
    private fun readNote(){
        try {
            val note = repository.retrieveNote()
            binding.noteText.setText(note)
        }
        catch (e: Exception){
            showToast("Can't read from file")
            e.printStackTrace()
        }
    }
    private fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getInternalFile(fileName:String): File {
        val path = filesDir
        return File(path,fileName)
    }
}