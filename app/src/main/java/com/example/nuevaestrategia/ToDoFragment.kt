package com.example.nuevaestrategia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ToDoFragment : Fragment() {
    private lateinit var listRecyclerView : RecyclerView
    private lateinit var myAdapter : RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmento=inflater.inflate(R.layout.fragment_to_to,container,false)


        /*

        Este codigo es cuando se hace uno a uno y no por fragmentos. si quieres lo despliegas

        val btnDetail1:Button=fragmento.findViewById(R.id.btn_detail_1)
        val btnDetail2:Button=fragmento.findViewById(R.id.btn_detail_2)
        val btnDetail3:Button=fragmento.findViewById(R.id.btn_detail_3)
        val btnDetail4:Button=fragmento.findViewById(R.id.btn_detail_4)

        btnDetail1.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", "nombre de la tarea 1")
            datos.putString("hora", "de 8:00am a 6:00pm")
            datos.putString("lugar", "Estadio Atanacio Girardot")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentosToDo,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        })

        btnDetail2.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", "nombre de la tarea 2")
            datos.putString("hora", "de 10:00am a 10:00pm")
            datos.putString("lugar", "En la mesa de la esquina")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentosToDo,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        })

        btnDetail3.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", "nombre de la tarea 3")
            datos.putString("hora", "de 14:00am a 50:00pm")
            datos.putString("lugar", "por ahi en cualquier lugar")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentosToDo,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        })

        btnDetail4.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", "nombre de la tarea 3")
            datos.putString("hora", "de 6:00am a 10pm")
            datos.putString("lugar", "a la discoteca")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fragmentosToDo,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        })*/

        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myTaskTitles : ArrayList<String> = ArrayList()
        var myTaskTimes : ArrayList<String> = ArrayList()
        var myTaskPlaces : ArrayList<String> = ArrayList()

        myTaskTitles.add("ir al super1")
        myTaskTitles.add("ir al super2")
        myTaskTitles.add("ir al super3")
        myTaskTitles.add("ir al super4")

        myTaskTimes.add("10:00am")
        myTaskTimes.add("10:10am")
        myTaskTimes.add("10:20am")
        myTaskTimes.add("10:30am")

        myTaskPlaces.add("Caramanta")
        myTaskPlaces.add("La Ceja")
        myTaskPlaces.add("Rionegro")
        myTaskPlaces.add("Santuario")

        var info : Bundle = Bundle()

        info.putStringArrayList("titles",myTaskTitles)
        info.putStringArrayList("times",myTaskTimes)
        info.putStringArrayList("places",myTaskPlaces)

        listRecyclerView= requireView().findViewById(R.id.recyclerToDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity,info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter= myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

    }

}