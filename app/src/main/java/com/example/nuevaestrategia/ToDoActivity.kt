package com.example.nuevaestrategia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import java.util.ServiceConfigurationError

class ToDoActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

     /*   if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentosToDo,ToDoFragment::class.java,null,"todo")
                .commit()

        } */

        setSupportActionBar(findViewById(R.id.miMenu))
        val fab:View = findViewById(R.id.btnFlotanteToDo)
        fab.setOnClickListener{ View-> }
        val drawerLayout: DrawerLayout =findViewById(R.id.drawer_layout)
        val navView: NavigationView= findViewById(R.id.nav_view)
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.fragmentosToDo)as NavHostFragment
        val navController= navHostFragment.navController
        appBarConfiguration= AppBarConfiguration(setOf(R.id.nav_todo,R.id.nav_about,R.id.nav_Welcome,R.id.nav_ProductosRR),drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentosToDo)as NavHostFragment
        val navController=navHostFragment.navController
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }
}