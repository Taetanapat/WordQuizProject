package com.example.android.navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.android.navigation.database.PlayerDataModel


class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var data = listOf<PlayerDataModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.nameText)
        val scoreText: TextView = itemView.findViewById(R.id.scoreText)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_highscore, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater
//                .inflate(R.layout.list_item_highscore, parent, false)
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        val res = holder.itemView.context.resources
//        holder.textView.text = item.sleepQuality.toString()
//        holder.bind(item)

        holder.bind(item)
    }

    private fun ViewHolder.bind(item: PlayerDataModel) {

        nameText.text = item.PlayerName
        scoreText.text = item.PlayerScore.toString()

    }
}
