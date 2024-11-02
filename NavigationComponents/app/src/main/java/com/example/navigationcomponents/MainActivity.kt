package com.example.navigationcomponents

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navcontroller: NavController
    private lateinit var appbarconfig: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
setSupportActionBar(findViewById(R.id.toolbar))
        appbarconfig=AppBarConfiguration(
            setOf(R.id.homeFrag,R.id.setting_frag,R.id.notification_Frag),
            drawerLayout = findViewById(R.id.main)
        )
        val novhostfrag=supportFragmentManager.findFragmentById(R.id.NavHost) as NavHostFragment
        navcontroller= novhostfrag.navController
        setupActionBarWithNavController(navcontroller,appbarconfig)
        findViewById<BottomNavigationView>(R.id.bottomnav).setupWithNavController(navcontroller)
        findViewById<NavigationView>(R.id.navview).setupWithNavController(navcontroller)
//        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navcontroller)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navcontroller.navigateUp(appbarconfig) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.setting_frag->{navcontroller.navigate(R.id.setting_frag)
            return true}
            R.id.about->{
                navcontroller.navigate(R.id.aboutApp_Frag)
                return true
            }
            else ->return super.onOptionsItemSelected(item)
        }

    }
}