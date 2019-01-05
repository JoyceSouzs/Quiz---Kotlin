package com.example.joycesouza.devappsprovakotlin

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterParticipante (private var activity: Activity, private var itens: ArrayList<Participante>): BaseAdapter() {


    private class ViewHolder(row: View?) {
        var nome: TextView? = null
        var pontuacao: TextView? = null

        init {
            this.nome = row?.findViewById<TextView>(R.id.txtNome)
            this.pontuacao = row?.findViewById<TextView>(R.id.txtPontuacao)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_itens, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var part = itens[position]
        viewHolder.nome?.text = part.mNome
        viewHolder.pontuacao?.text = part.mPontuacao

        return view as View
    }

    override fun getItem(position: Int): Participante {
        return itens[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itens.size
    }
}