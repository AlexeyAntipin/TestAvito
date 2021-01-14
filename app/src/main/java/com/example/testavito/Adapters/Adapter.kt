package com.example.testavito.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.testavito.R


class Adapter(private val dataSet: MutableList<String>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView
        val delete: Button
        val cardView: CardView

        init {
            number = view.findViewById(R.id.number)
            delete = view.findViewById(R.id.delete)
            cardView = view.findViewById(R.id.card_view)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.number.text = dataSet[position]

        viewHolder.delete.setOnClickListener { listener(position) }

        viewHolder.cardView.animation =
            AnimationUtils.loadAnimation(
                viewHolder.cardView.context,
                R.anim.recycler_view_animation
            )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}