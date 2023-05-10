package com.example.theghostappv3.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theghostappv3.R

class textOutputAdapter(private val outputList: List<String>) : RecyclerView.Adapter<textOutputAdapter.ViewHolder>() {

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val outputText: TextView = view.findViewById(R.id.textView_txtOutput)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.textoutput, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return outputList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.outputText.text = outputList[position]
    }
}