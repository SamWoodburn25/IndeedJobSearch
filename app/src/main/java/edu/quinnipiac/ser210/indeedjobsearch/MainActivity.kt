/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * main activity- sets the nav controller and toolbar
 */

package edu.quinnipiac.ser210.jobsearch

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupMenu
import android.widget.ShareActionProvider
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting the Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Indeed Job Search"

        // Finding the Navigation Host Fragment and getting NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setting Up AppBarConfiguration and integrating with NavController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    //navigate back/up
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    //toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        // Configure ShareActionProvider
        val shareItem = menu?.findItem(R.id.action_share)
        val shareActionProvider = androidx.core.view.MenuItemCompat.getActionProvider(shareItem!!) as? androidx.appcompat.widget.ShareActionProvider

        // Create an Intent to share your content
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "This is a message for you.")
        }

        // Attach the share intent to the ShareActionProvider
        shareActionProvider?.setShareIntent(shareIntent)

        return true
    }

    //handle option menu clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when(id){
            R.id.action_help -> {
                val navController = findNavController(R.id.nav_host_fragment)
                val action = when (navController.currentDestination?.id) {
                    R.id.homeFragment -> HomeFragmentDirections.actionHomeFragmentToHelpFragment()
                    R.id.allJobFragment2 -> AllJobFragmentDirections.actionAllJobFragment2ToHelpFragment()
                    else -> null
                }
                action?.let { navController.navigate(it) }
                return true
            }
            R.id.action_setting -> {
                showSettingsMenu(findViewById(item.itemId))
                true
            }
            R.id.action_share -> {
                true
            }
            else -> NavigationUI.onNavDestinationSelected(item!!, navController)
                    ||super.onOptionsItemSelected(item)
        }
    }


    //settings menu
    private fun showSettingsMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.settings, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            // Handle submenu item clicks
            when (menuItem.itemId) {
                R.id.pink_setting -> {
                    changeBackgroundColor(R.color.pibkBackground)
                    true
                }
                R.id.orange_setting -> {
                    changeBackgroundColor(R.color.orangeBackground)
                    true
                }
                R.id.green_setting -> {
                    changeBackgroundColor(R.color.greenBackground)
                    true
                }
                R.id.blue_setting -> {
                    changeBackgroundColor(R.color.blueBackground)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
    fun changeBackgroundColor(color: Int) {
        val layout = findViewById<FrameLayout>(R.id.main_layout)
        layout.setBackgroundColor(ContextCompat.getColor(this, color))
    }

}
