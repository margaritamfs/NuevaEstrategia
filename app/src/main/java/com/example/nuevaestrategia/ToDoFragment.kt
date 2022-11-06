package com.example.nuevaestrategia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import androidx.fragment.app.Fragment

class ToDoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmento=inflater.inflate(R.layout.fragment_to_to,container,false)
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
        })

        return fragmento
    }

}