package com.ahmeteminyilmaz.todo.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ahmeteminyilmaz.todo.R

class HomePageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.todo_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.add_taskOrActivity_item -> {
                val action = HomePageFragmentDirections.actionHomePageToAddingTaskOrActivity("camefrommenu")
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.myTasksAndActivities_item -> {
                val action = HomePageFragmentDirections.actionHomePageToToDoListFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.weather_item -> {
                val action = HomePageFragmentDirections.actionHomePageToWeatherFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.aboutApp_item -> {
                val action = HomePageFragmentDirections.actionHomePageToAboutAppPage()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
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