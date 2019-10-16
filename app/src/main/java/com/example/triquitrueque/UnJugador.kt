package com.example.triquitrueque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UnJugador : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_jugador)

        var mundos:MutableList<Mundos> = ArrayList()

        mundos.add(Mundos(nombre = "Estados Unidos", foto = R.drawable.BEstadosUnidos162))
        mundos.add(Mundos(nombre = "China", foto = R.drawable.BChina162))
        mundos.add(Mundos(nombre = "Francia", foto = R.drawable.BFrancia162))


        recyclerView=findViewById(R.id.recycler) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false
        )

        val mundosAdapter = MundosAdapter(mundos!!, this)

        recyclerView.adapter = mundosAdapter
    }
}


