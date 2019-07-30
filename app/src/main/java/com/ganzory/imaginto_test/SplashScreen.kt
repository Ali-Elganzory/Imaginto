package com.ganzory.imaginto_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

/****   Made by Ali Elganzory 29/7/2019    ****/

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()

        object : CountDownTimer(2000L, 2000L){
            override fun onFinish() {
                startActivity(Intent(applicationContext, Menu::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {            }

        }.start()
    }

}
