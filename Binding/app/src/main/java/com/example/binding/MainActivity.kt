package com.example.binding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.binding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            initButtons()
            initUIs()
    }
    private fun initButtons(){
        binding.apply{
            countButton.setOnClickListener {
                if(upRadioButton.isChecked){
                    if(tenCheckbox.isChecked){
                        count += 10
                    }else {
                        count++
                    }
                }
                else if (downRadioButton.isChecked){
                    if(tenCheckbox.isChecked){
                        count -= 10
                    }else{
                        count--
                    }
                }
                else{
                    System.err.println("Something wrong with the program")
                }
                counterTextview.text = count.toString()
            }
        }
    }
    private fun initUIs(){
        binding.counterTextview.text = count.toString()
    }
}