package com.example.kalkulator

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val precisionButton1: Button = findViewById(R.id.precision1)
        val precisionButton2: Button = findViewById(R.id.precision2)
        val precisionButton3: Button = findViewById(R.id.precision3)
        val arrowButton1: Button = findViewById(R.id.arrow1)
        val arrowButton2: Button = findViewById(R.id.arrow2)
        val arrowButton3: Button = findViewById(R.id.arrow3)
        val backButton: Button = findViewById(R.id.backButton)
        var prec: Int = intent.getIntExtra("precision", 2)
        var arr: String? = intent.getStringExtra("arrow")

        fun disp(){
            when(prec){
                1 -> {
                    precisionButton1.setBackgroundColor(Color.rgb(30, 52, 92))
                    precisionButton2.setBackgroundColor(Color.rgb(59, 97, 168))
                    precisionButton3.setBackgroundColor(Color.rgb(59, 97, 168))
                }
                2 -> {
                    precisionButton1.setBackgroundColor(Color.rgb(59, 97, 168))
                    precisionButton2.setBackgroundColor(Color.rgb(30, 52, 92))
                    precisionButton3.setBackgroundColor(Color.rgb(59, 97, 168))
                }
                3 -> {
                    precisionButton1.setBackgroundColor(Color.rgb(59, 97, 168))
                    precisionButton2.setBackgroundColor(Color.rgb(59, 97, 168))
                    precisionButton3.setBackgroundColor(Color.rgb(30, 52, 92))
                }
            }

            when(arr){
                "→" -> {
                    arrowButton1.setBackgroundColor(Color.rgb(30, 52, 92))
                    arrowButton2.setBackgroundColor(Color.rgb(59, 97, 168))
                    arrowButton3.setBackgroundColor(Color.rgb(59, 97, 168))
                }
                "-->" -> {
                    arrowButton1.setBackgroundColor(Color.rgb(59, 97, 168))
                    arrowButton2.setBackgroundColor(Color.rgb(30, 52, 92))
                    arrowButton3.setBackgroundColor(Color.rgb(59, 97, 168))
                }
                "==>" -> {
                    arrowButton1.setBackgroundColor(Color.rgb(59, 97, 168))
                    arrowButton2.setBackgroundColor(Color.rgb(59, 97, 168))
                    arrowButton3.setBackgroundColor(Color.rgb(30, 52, 92))
                }
            }
        }
        disp()

        fun choosePrecision(p: Int){
            prec = p
            disp()
        }

        fun chooseArrow(s: String){
            arr = s
            disp()
        }

        fun back(){
            val returnIntent = Intent()
            returnIntent.putExtra("returnPrecision", prec)
            returnIntent.putExtra("returnArrow", arr)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        precisionButton1.setOnClickListener{ choosePrecision(1) }
        precisionButton2.setOnClickListener{ choosePrecision(2) }
        precisionButton3.setOnClickListener{ choosePrecision(3) }
        arrowButton1.setOnClickListener{ chooseArrow("→") }
        arrowButton2.setOnClickListener{ chooseArrow("-->") }
        arrowButton3.setOnClickListener{ chooseArrow("==>") }
        backButton.setOnClickListener{ back() }
    }
}