package com.example.searchingimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.searchingimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val logTag = MainActivity::class.simpleName ?: ""
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 네비게이션
        val navController = (supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment).findNavController()
        binding.bottomNav.setupWithNavController(navController)
    }
}