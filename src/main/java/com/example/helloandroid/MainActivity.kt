package com.example.helloandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent


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
        //function to create a button listener, takes in the button id as a parameter
        fun createButtonListener(buttonid: Int){
            //finds button with the given button id
            findViewById<Button>(buttonid).setOnClickListener {
                //creates the intent for the button to send to the second activity
                val intent = Intent(this, SecondActivity::class.java)
                //puts the button text data as data to send with the intent
                intent.putExtra("text", (it as Button).text.toString())
                //start second activity
                startActivity(intent)
            }
        }

        //create all 5 button listeners
        createButtonListener(R.id.button)
        createButtonListener(R.id.button2)
        createButtonListener(R.id.button3)
        createButtonListener(R.id.button4)
        createButtonListener(R.id.button5)

    }
}

