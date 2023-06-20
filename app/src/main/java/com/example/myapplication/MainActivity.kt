package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.adapter.ListHeroAdapter
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentCalculator.ResultCalListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var myPreference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        if (savedInstanceState == null) {
            navController.navigate(R.id.fragmentTipCalculator)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolBar.title = destination.label
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigate -> {
                if (::navController.isInitialized) {
                    if (navController.currentDestination?.id == R.id.fragmentTipCalculator) {
                        navController.navigate(R.id.action_fragmentTipCalculator_to_fragmentCalculator)
                    } else {
                        navController.navigate(R.id.action_fragmentCalculator_to_fragmentTipCalculator)
                    }
                    return true
                }
                super.onOptionsItemSelected(item)
            }
            R.id.saveResult -> {
                if (::navController.isInitialized){
                    if (item.isCheckable){
                        item.setCheckable(false)
                        // i want call saveResultCal here
                    }
                }
                super.onOptionsItemSelected(item)
            }
            R.id.navigate2 -> {
                if (::navController.isInitialized){
                    navController.navigate(R.id.action_fragmentTipCalculator_to_fragmentListHero)
                }
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun saveResultCal(result: String) {
        myPreference.setResultCal(result)
    }
}
