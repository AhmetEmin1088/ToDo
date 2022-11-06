package com.ahmeteminyilmaz.todo.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.ahmeteminyilmaz.todo.R

class MainActivity : AppCompatActivity() {

    //48c1d3e2d53046a44b9c7ac34c63b2e0
    //https://api.openweathermap.org/data/2.5/weather?q=Kayseri,tr&APPID=48c1d3e2d53046a44b9c7ac34c63b2e0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.todo_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.add_taskOrActivity_item -> {

                val action = HomePageFragmentDirections.actionHomePageToAddingTaskOrActivity()
                Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action)

            }
            R.id.weather_item -> {

                val action = HomePageFragmentDirections.actionHomePageToWeatherFragment()
                Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action)

            }
            R.id.aboutApp_item -> {

                val action = HomePageFragmentDirections.actionHomePageToAboutAppPage()
                Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action)


            }
            R.id.myTasksAndActivities_item -> {

                val action = HomePageFragmentDirections.actionHomePageToToDoListFragment()
                Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action)

            }
            R.id.exitApp_item -> {

                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog
                    .setTitle("Exit App")
                    .setMessage("Are you sure do you want to exit?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .create()
                    .show()

            }
        }

        return super.onOptionsItemSelected(item)
    }

     */






}