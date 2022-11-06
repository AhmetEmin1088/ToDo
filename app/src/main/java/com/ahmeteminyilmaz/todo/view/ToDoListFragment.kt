package com.ahmeteminyilmaz.todo.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmeteminyilmaz.todo.R
import com.ahmeteminyilmaz.todo.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {

    var taskOrActivityTitleList = ArrayList<String>()
    var taskOrActivityIdList = ArrayList<Int>()
    private lateinit var listAdapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        listAdapter = RecyclerAdapter(taskOrActivityTitleList,taskOrActivityIdList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listAdapter

        sqlGetData()

        super.onViewCreated(view, savedInstanceState)
    }

    fun sqlGetData() {

        try {

            activity?.let {
                val database = it.openOrCreateDatabase("TasksOrActivities", Context.MODE_PRIVATE,null)

                val cursor = database.rawQuery("SELECT * FROM tasksoractivities",null)
                val titleIndex = cursor.getColumnIndex("title")
                val idIndex = cursor.getColumnIndex("id")

                taskOrActivityTitleList.clear()
                taskOrActivityIdList.clear()

                while (cursor.moveToNext()) {

                    taskOrActivityTitleList.add(cursor.getString(titleIndex))
                    taskOrActivityIdList.add(cursor.getInt(idIndex))

                }

                listAdapter.notifyDataSetChanged()

                cursor.close()

            }



        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.todo_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.add_taskOrActivity_item -> {
                val action = ToDoListFragmentDirections.actionToDoListFragmentToAddingTaskOrActivity("camefrommenu")
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.myTasksAndActivities_item -> {
                Toast.makeText(requireActivity(),"You are already in this page.",Toast.LENGTH_LONG).show()
            }
            R.id.weather_item -> {
                val action = ToDoListFragmentDirections.actionToDoListFragmentToWeatherFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.aboutApp_item -> {
                val action = ToDoListFragmentDirections.actionToDoListFragmentToAboutAppPage()
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