package com.example.sharedpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreference.databinding.ActivityProtectedBinding

class ProtectedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProtectedBinding
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProtectedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.USER_CREDENTIALS_FILE,MODE_PRIVATE)
        initTexts()
        initButtons()
    }
    private fun initTexts() {
// To be implemented
        val username = prefs.getString(Constants.USERNAME_KEY,null)
        binding.infoText.text = "Hi, $username All the best in the next exam!"
    }
    private fun initButtons() {
        binding.logoutButton.setOnClickListener {
// To be implemented
            with(prefs.edit()) {
                clear()
                apply()
            }
                finish()
        }
    }
}