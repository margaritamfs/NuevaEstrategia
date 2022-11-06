package com.example.nuevaestrategia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmento=inflater.inflate(R.layout.fragment_detail,container,false)
        var tarea=requireArguments().getString("tarea")
        var hora=requireArguments().getString("hora")
        var lugar=requireArguments().getString("lugar")
        var descripcion=requireArguments().getString("descripcion")

        var tarea1 : TextView=fragmento.findViewById(R.id.tarea1)
        var tarea2 : TextView=fragmento.findViewById(R.id.tarea2)
        var tarea3 : TextView=fragmento.findViewById(R.id.tarea3)
        var tarea4 : TextView=fragmento.findViewById(R.id.tarea4)

        tarea1.text=tarea
        tarea2.text=hora
        tarea3.text=lugar
        tarea4.text=descripcion

        return fragmento
    }
}