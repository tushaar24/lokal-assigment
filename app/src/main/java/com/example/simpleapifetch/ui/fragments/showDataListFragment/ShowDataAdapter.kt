package com.example.simpleapifetch.ui.fragments.showDataListFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleapifetch.R
import com.example.simpleapifetch.databinding.ItemDataLayoutBinding
import com.example.simpleapifetch.model.SimpleData
import com.example.simpleapifetch.model.SimpleDataItem

class ShowDataAdapter(
    private val mContext: Context,
    private val onClick: (String, String) -> Unit
) : RecyclerView.Adapter<ShowDataAdapter.ShowDataViewHolder>() {

    private var dataList: MutableList<SimpleDataItem> = mutableListOf()

    class ShowDataViewHolder(private val binding: ItemDataLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            simpleDataItem: SimpleDataItem,
            mContext: Context,
            onClick: (String, String) -> Unit
        ) {
            binding.tvTitle.text = simpleDataItem.title
            Glide.with(mContext)
                .load(simpleDataItem.url)
                .placeholder(R.drawable.demo)
                .into(binding.ivDataImage)

            binding.root.setOnClickListener {
                onClick(simpleDataItem.url, simpleDataItem.title)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ShowDataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDataLayoutBinding.inflate(layoutInflater, parent, false)
                return ShowDataViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowDataViewHolder {
        return ShowDataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShowDataViewHolder, position: Int) {
        val currentData = dataList[position]
        holder.bind(currentData, mContext, onClick)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setDataList(newDataList: SimpleData) {
        dataList.clear()

        for (simpleData in newDataList) {
            dataList.add(simpleData)
            notifyDataSetChanged()
        }
    }
}