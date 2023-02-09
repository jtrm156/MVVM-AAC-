package com.example.practicemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        showMainFragment()
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction().replace(binding.mainContainer.id, MainFragment()).commit()
    }
}