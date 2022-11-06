package com.ahmeteminyilmaz.todo.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ahmeteminyilmaz.todo.R

class AboutAppFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_app_page, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.todo_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.add_taskOrActivity_item -> {
                val action = AboutAppFragmentDirections.actionAboutAppPageToAddingTaskOrActivity("camefrommenu")
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.myTasksAndActivities_item -> {
                val action = AboutAppFragmentDirections.actionAboutAppPageToToDoListFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.weather_item -> {
                val action = AboutAppFragmentDirections.actionAboutAppPageToWeatherFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.aboutApp_item -> {
                Toast.makeText(requireActivity(),"You are already in this page.",Toast.LENGTH_LONG).show()
            }
            R.id.exitApp_item -> {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog
                    .setTitle("Exit App")
                    .setMessage("Are you sure do you want to exit?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        requireActivity().finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .create()
                    .show()
            }

        }

        return super.onOptionsItemSelected(item)
    }





}