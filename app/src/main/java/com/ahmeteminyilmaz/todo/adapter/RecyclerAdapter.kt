package com.ahmeteminyilmaz.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmeteminyilmaz.todo.R
import com.ahmeteminyilmaz.todo.view.ToDoListFragmentDirections
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val toDoList : ArrayList<String>, val idList : ArrayList<Int>) : RecyclerView.Adapter<RecyclerAdapter.TaskOrActivityHolder>() {

    class TaskOrActivityHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOrActivityHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return TaskOrActivityHolder(view)


    }

    override fun onBindViewHolder(holder: TaskOrActivityHolder, position: Int) {

        holder.itemView.toDoText_recyclerView.text = toDoList[position]
        holder.itemView.setOnClickListener {

            val action = ToDoListFragmentDirections.actionToDoListFragmentToAddingTaskOrActivity("camefromrecycler",idList[position])
            Navigation.findNavController(it).navigate(action)

        }


    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

}