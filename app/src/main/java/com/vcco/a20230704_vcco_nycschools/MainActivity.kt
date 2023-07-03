package com.vcco.a20230704_vcco_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //NavigationUI.setupActionBarWithNavController(this, navController())
    }

    override fun onSupportNavigateUp() = navController().navigateUp()

    private fun navController() = findNavController(R.id.navHostFragment)
}
