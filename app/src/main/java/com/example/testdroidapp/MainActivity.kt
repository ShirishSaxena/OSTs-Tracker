package com.example.testdroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var url : String = "jdbc:mysql://127.0.0.1:3306/testdb"
    private var user : String = "root"
    private var pass : String = "root"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}