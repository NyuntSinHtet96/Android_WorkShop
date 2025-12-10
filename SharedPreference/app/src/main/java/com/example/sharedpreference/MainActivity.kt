package com.example.sharedpreference

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.USER_CREDENTIALS_FILE,MODE_PRIVATE)
        initButtons()
        tryStartingProtectedActivity()
    }

    private fun tryStartingProtectedActivity() {
// To be implemented
        if (prefs.contains(Constants.USERNAME_KEY) && prefs.contains(Constants.PASSWORD_KEY)){
                val username = prefs.getString(Constants.USERNAME_KEY,null)
                val password = prefs.getString(Constants.PASSWORD_KEY,null)
                val ok = logIn(username,password)
                if(ok) {
                    startProtectedActivity()
                }
            }
    }

    private fun initButtons() {
        binding.apply {
            loginButton.setOnClickListener {
// To be implemented
                val username = usernameText.text.toString()
                val password = passwordText.text.toString()
                val ok = logIn(username,password)
                if (ok){
                    val editor = prefs.edit()
                    editor.putString(Constants.USERNAME_KEY,username)
                    editor.putString(Constants.PASSWORD_KEY,password)
                    startProtectedActivity()
                    editor.apply()
                }else{
                    showToast("Credentials are not valid")
                }
            }
        }
    }

    private fun logIn(username: String?, password: String?): Boolean {
        return username == "DipSA" && password == "DipSA"
    }

    private fun startProtectedActivity() {
        val intent = Intent(this, ProtectedActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(
            applicationContext, message, Toast.LENGTH_SHORT
        ).show()

    }
}