package com.gomez.herlin.logindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gomez.herlin.logindemo.R
import com.gomez.herlin.logindemo.dto.DonutsDto

class ListAdapter(
    private var donutsDtoList: List<DonutsDto>,
    private val context: Context,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val minflater: LayoutInflater = LayoutInflater.from(context)

    interface OnItemClickListener {
        fun onItemClick(item: DonutsDto?)
    }

    override fun getItemCount(): Int {
        return donutsDtoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cv.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition)
        holder.bindData(donutsDtoList[position])
    }

    fun setItems(items: List<DonutsDto>) {
        donutsDtoList = items
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.idTextView)
        var type: TextView = itemView.findViewById(R.id.typeTextView)
        var name: TextView = itemView.findViewById(R.id.nameTextView)
        var cv: CardView = itemView.findViewById(R.id.cv)

        fun bindData(donutsDto: DonutsDto) {
            id.text = donutsDto.id
            type.text = donutsDto.type
            name.text = donutsDto.name
            itemView.setOnClickListener {
                listener.onItemClick(
                    donutsDto
                )
            }
        }
    }
}