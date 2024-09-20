package com.example.weather

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {

    private var editor:SharedPreferences.Editor?=null
    private var nightMode:Boolean=false
    private var sharedPreferences:SharedPreferences ?= null
    private lateinit var modeSwitch:Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        modeSwitch = findViewById(R.id.sw)

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        nightMode = sharedPreferences?.getBoolean("night", false)!!

        if (nightMode){
            modeSwitch.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            modeSwitch.isChecked=false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        modeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (nightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor=sharedPreferences?.edit()
                editor?.putBoolean("night", false)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor=sharedPreferences?.edit()
                editor?.putBoolean("night", true)
            }
            editor?.apply()
        }



    }
}