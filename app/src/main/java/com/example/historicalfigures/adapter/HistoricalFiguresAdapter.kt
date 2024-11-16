package com.example.historicalfigures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.databinding.ItemHistoricalFigureBinding
import com.example.historicalfigures.model.HistoricalFigure

class HistoricalFiguresAdapter : ListAdapter<HistoricalFigure, HistoricalFiguresAdapter.HistoricalFigureViewHolder>(HistoricalFigureDiffCallback()) {


    private var fullList = listOf<HistoricalFigure>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalFigureViewHolder {
        val binding = ItemHistoricalFigureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoricalFigureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricalFigureViewHolder, position: Int) {
        val figure = getItem(position)
        holder.bind(figure)
    }

    inner class HistoricalFigureViewHolder(private val binding: ItemHistoricalFigureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(figure: HistoricalFigure) {
            binding.nameTextView.text = figure.name
            binding.titleTextView.text = figure.title
            binding.occupationTextView.text = figure.occupation?.joinToString(", ")
        }
    }

    class HistoricalFigureDiffCallback : DiffUtil.ItemCallback<HistoricalFigure>() {
        override fun areItemsTheSame(oldItem: HistoricalFigure, newItem: HistoricalFigure): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: HistoricalFigure, newItem: HistoricalFigure): Boolean {
            return oldItem == newItem
        }
    }


    override fun submitList(list: List<HistoricalFigure>?) {
        fullList = list ?: emptyList()
        super.submitList(list)
    }


    fun filter(query: String) {
        val filteredList = fullList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.title.contains(query, ignoreCase = true) ||
                    it.occupation.joinToString(", ").contains(query, ignoreCase = true)
        }
        submitList(filteredList)
    }
}