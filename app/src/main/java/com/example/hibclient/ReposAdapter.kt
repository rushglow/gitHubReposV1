package com.example.hibclient

import android.R.color
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ReposAdapter(var repos: ArrayList<Repositories>, colors: Map<String, String>):RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {
    var update = ""
    var colors = colors

    class ReposViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameTv = itemView.findViewById<TextView>(R.id.name_tv)
        val updateTv = itemView.findViewById<TextView>(R.id.update_tv)
        val descriptionTv = itemView.findViewById<TextView>(R.id.description_tv)
        val stargazersTv = itemView.findViewById<TextView>(R.id.stargaze_count_tv)
        val branchTv = itemView.findViewById<TextView>(R.id.forks_count_tv)
        val languageTv = itemView.findViewById<TextView>(R.id.lang_tv)
        val languageColor = itemView.findViewById<ImageView>(R.id.lang_ic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_item, parent, false)
        return ReposViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        update = "${repos[position].updated_at?.get(8)}${repos[position].updated_at?.get(9)}.${repos[position].updated_at?.get(5)}${repos[position].updated_at?.get(6)}.${repos[position].updated_at?.get(0)}${repos[position].updated_at?.get(1)}${repos[position].updated_at?.get(2)}${repos[position].updated_at?.get(3)}"
        holder.updateTv.text = update
        holder.descriptionTv.text = repos[position].description
        holder.stargazersTv.text = repos[position].stargazers_count.toString()
        holder.branchTv.text = repos[position].forks_count.toString()
        holder.languageTv.text = repos[position].language
        holder.nameTv.text = repos[position].name
        var colorHex = colors.get(repos[position].language)
        if (colorHex != null){
            holder.languageColor.setColorFilter(Color.parseColor(colorHex))
        }else{
            holder.languageColor.visibility = View.INVISIBLE
        }

    }
}