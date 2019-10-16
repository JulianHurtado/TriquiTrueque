package com.example.triquitrueque

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.mundos_item.view.*



class MundosAdapter : RecyclerView.Adapter<MundosAdapter.MundosViewHolder> {

    private var listmundos: List<Mundos> ?= null
    private var context: Context ?= null

    constructor(listmundos : List<Mundos>, context: Context){
        this.listmundos=listmundos
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MundosViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.mundos_item,parent, false)
        return MundosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listmundos?.size!!
    }

    override fun onBindViewHolder(holder: MundosViewHolder, position: Int) {
        var mundos = listmundos!![position]
        holder.loadItem(mundos)
    }

    class MundosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun loadItem(mundos: Mundos) {
            itemView.nombre_mundo.text = mundos.nombre
            itemView.foto_mundo.setImageResource(mundos.foto)
        }
    }


}