package com.tubagusapp.shirohigefood.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.tubagusapp.shirohigefood.R
import com.tubagusapp.shirohigefood.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWithNavController(
            binding.homeBottomNavView,
            (supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment).navController
        )
    }
}