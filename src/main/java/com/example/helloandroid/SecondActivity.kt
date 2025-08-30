package com.example.helloandroid

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //create a button listener for the back button, which goes finishes the second activity and returns to the first one
        findViewById<Button>(R.id.back).setOnClickListener {
            finish()
        }

        //create a button text variable using the extra data from the intent from main activity
        val buttonText = intent.getStringExtra("text")
        //display button text from main onto the text display
        findViewById<TextView>(R.id.textDisplay).text = buttonText
    }
}