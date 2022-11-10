package com.example.nuevaestrategia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyTaskListAdapter (context : AppCompatActivity, val info : Bundle)
    : RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>()  {

    class MyViewHolder(val layout : View) : RecyclerView.ViewHolder(layout)

    private var context : AppCompatActivity = context

    var myTaskTitles : ArrayList<String> = info.getStringArrayList("titles") as ArrayList<String>
    var myTasktimes : ArrayList<String> = info.getStringArrayList("times") as ArrayList<String>
    var myTaskPlaces : ArrayList<String> = info.getStringArrayList("places") as ArrayList<String>
    var myTaskIds : ArrayList<String> = info.getStringArrayList("ids") as ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.task_list_items,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var textViewTask = holder.layout.findViewById<TextView>(R.id.textViewTask)
        textViewTask.text=myTaskTitles[position]

        var txtViewTime = holder.layout.findViewById<TextView>(R.id.textViewTime)
        txtViewTime.text=myTasktimes[position]

        holder.layout.setOnClickListener{
            Toast.makeText(holder.itemView.context,textViewTask.text,Toast.LENGTH_LONG).show()
            val datos = Bundle()
            datos.putString("tarea",textViewTask.text as String)
            datos.putString("hora",txtViewTime.text as String)
            datos.putString("lugar",myTaskPlaces[position])
            datos.putString("id", myTaskIds[position])

            context.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentosToDo,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        }

    }

    override fun getItemCount(): Int {
        return myTaskTitles.size
    }

}