package com.example.diseoaplicacionbienestar


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MoodAdapter(private val moodList: List<String>) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    class MoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewMood: TextView = view.findViewById(R.id.textViewMood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.textViewMood.text = moodList[position]
    }

    override fun getItemCount(): Int = moodList.size
}
