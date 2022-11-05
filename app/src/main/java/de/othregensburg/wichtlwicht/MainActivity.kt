package de.othregensburg.wichtlwicht

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import de.othregensburg.wichtlwicht.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_normal -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ListFragment)
                Toast.makeText(applicationContext, "normal Wichteln", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_tasks -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.TasksFragment)
                Toast.makeText(applicationContext, "Aufgaben verteilen", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_result -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ResultFragment)
                Toast.makeText(applicationContext, "Ergebnisse", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_abort -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ListFragment)
                Toast.makeText(applicationContext, "Abbruch", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}