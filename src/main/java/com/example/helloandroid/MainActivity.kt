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

        fun createButtonListener(button_id: Int){
            findViewById<Button>(button_id).setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("text", (it as Button).text.toString())
                startActivity(intent)
            }
        }

        createButtonListener(R.id.button)
        createButtonListener(R.id.button2)
        createButtonListener(R.id.button3)
        createButtonListener(R.id.button4)
        createButtonListener(R.id.button5)

    }
}

