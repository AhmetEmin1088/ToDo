package com.ahmeteminyilmaz.todo.view

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.ahmeteminyilmaz.todo.R
import kotlinx.android.synthetic.main.fragment_adding_task_or_activity.*

class AddingTaskOrActivityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adding_task_or_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            add(it)
        }

        deleteButton.setOnClickListener {
            delete(it)
        }

        arguments?.let {

            val receievedInformation = AddingTaskOrActivityFragmentArgs.fromBundle(it).information

            if(receievedInformation.equals("camefrommenu")) {

                taskOrActivityTitleText.setText("")
                explanationText.setText("")
                addButton.visibility = View.VISIBLE
                deleteButton.visibility = View.GONE

            }else{

                addButton.visibility = View.INVISIBLE
                deleteButton.visibility = View.VISIBLE

                val chosenId = AddingTaskOrActivityFragmentArgs.fromBundle(it).id

                context?.let {

                    try {

                        val db = it.openOrCreateDatabase("TasksOrActivities",Context.MODE_PRIVATE,null)
                        val cursor = db.rawQuery("SELECT * FROM tasksoractivities WHERE id = ?", arrayOf(chosenId.toString()))

                        val titleIndex = cursor.getColumnIndex("title")
                        val explanationIndex = cursor.getColumnIndex("explanation")

                        while(cursor.moveToNext()) {

                            taskOrActivityTitleText.setText(cursor.getString(titleIndex))
                            explanationText.setText(cursor.getString(explanationIndex))
                            taskOrActivityTitleText.setBackgroundColor(Color.TRANSPARENT)
                            explanationText.setBackgroundColor(Color.TRANSPARENT)

                        }
                        cursor.close()

                    }catch (e : Exception) {
                        e.printStackTrace()
                    }

                }

            }

        }


    }

    fun add(view : View) {

        val taskOrActivityTitle = taskOrActivityTitleText.text.toString()
        val explanation = explanationText.text.toString()

        try {

            context?.let {
                val dataBase = it.openOrCreateDatabase("TasksOrActivities",Context.MODE_PRIVATE,null)
                dataBase.execSQL("CREATE TABLE IF NOT EXISTS tasksoractivities(id INTEGER PRIMARY KEY, title VARCHAR, explanation VARCHAR)")

                val sqlString = "INSERT INTO tasksoractivities (title, explanation) VALUES (?,?)"
                val statement = dataBase.compileStatement(sqlString)
                statement.bindString(1,taskOrActivityTitle)
                statement.bindString(2,explanation)
                statement.execute()

            }

        }catch (e : Exception) {
            e.printStackTrace()
        }

        val action = AddingTaskOrActivityFragmentDirections.actionAddingTaskOrActivityToToDoListFragment()
        Navigation.findNavController(view).navigate(action)

    }

    fun delete(view: View) {

        val taskOrActivityTitle = taskOrActivityTitleText.text.toString()

        try {

            context?.let {

                val dataBase = it.openOrCreateDatabase("TasksOrActivities",Context.MODE_PRIVATE,null)

                val sqlString = "DELETE FROM tasksoractivities WHERE title = ?"
                val statement = dataBase.compileStatement(sqlString)
                statement.bindString(1,taskOrActivityTitle)
                statement.execute()


            }



        }catch (e : Exception) {
            e.printStackTrace()
        }

        val action = AddingTaskOrActivityFragmentDirections.actionAddingTaskOrActivityToToDoListFragment()
        Navigation.findNavController(view).navigate(action)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.todo_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.add_taskOrActivity_item -> {
                Toast.makeText(requireActivity(),"You are already in this page.",Toast.LENGTH_LONG).show()
            }
            R.id.myTasksAndActivities_item -> {
                val action = AddingTaskOrActivityFragmentDirections.actionAddingTaskOrActivityToToDoListFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.weather_item -> {
                val action = AddingTaskOrActivityFragmentDirections.actionAddingTaskOrActivityToWeatherFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.aboutApp_item -> {
                val action = AddingTaskOrActivityFragmentDirections.actionAddingTaskOrActivityToAboutAppPage()
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