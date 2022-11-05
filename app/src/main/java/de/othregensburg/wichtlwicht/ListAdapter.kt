package de.othregensburg.wichtlwicht

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView



class ListAdapter(private val data: List<String>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameItem: TextView = view.findViewById(R.id.participantName)
        val rowLayout: ConstraintLayout = view.findViewById(R.id.rowlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = data[position]
        holder.nameItem.text = item

    }

    override fun getItemCount(): Int {
        return data.size
    }

}