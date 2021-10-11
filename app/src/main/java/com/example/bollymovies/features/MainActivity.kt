package com.example.bollymovies.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.bollymovies.R
import com.example.bollymovies.databinding.ActivityMainBinding
import com.example.bollymovies.features.login.view.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myToolbar = binding.toolbar
        setSupportActionBar(myToolbar)


        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =  when (item.itemId) {
            R.id.myAccount -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.search -> {
                //MODELO DE TRANSIÇÃO FRAGMENTS
//                var myListFragment = MyListFragment()
//                val transaction = this.supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.nav_host_fragment, myListFragment)
//                transaction.commit()

                true
            }
        else -> {
            super.onOptionsItemSelected(item)
        }
        }

}
