package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemviewBinding

class CusAdapter(var datalist:ArrayList<model>,var context:Context,
                 private var actionmodelistner:actionmodelistener):RecyclerView.Adapter<CusAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemviewBinding):RecyclerView.ViewHolder(binding.root){
        //ek inner class create karengay viewholder view ko hold karne k liye
    }
private val si= mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //jab run hoga tab kya hona
        //view create hoga
        //viewholder create hoga
        //inflate kar k phir return karengay viewholdaer ko
        val binding=ItemviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //yahan pe values set karengay
        anim(holder.itemView)
        holder.binding.imageView.setImageResource(datalist[position].profile)
        holder.binding.textView.text=datalist[position].name
        holder.binding.textView2.text=datalist[position].country

//highlight selected items
        if(si.contains(position)){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent))
        }
//adapter mein action mode on hoga long press pe
        holder.itemView.setOnLongClickListener {
            actionmodelistner.startActionMode()
            toggleSelection(position)
            true
        }

        //normal click pe kya hoga
        holder.itemView.setOnClickListener(){
            if(si.isNotEmpty()){
                toggleSelection(position)
            }
            else{
                Toast.makeText(context,datalist[position].name,Toast.LENGTH_SHORT).show()
            }
        }

    }
//toggle postion of the selected items
    private fun toggleSelection(position: Int) {
         if(si.contains(position)){
             si.remove(position)
         }
        else {
            si.add(position)
         }
        notifyItemChanged(position)
    }
//delete selected items
    fun deleteSelectedItems() {
        val selectedItems = si.toList().sortedDescending()
    selectedItems.forEach { datalist.removeAt(it) }
    si.clear()
    notifyDataSetChanged()
    }
//to clear selected items
    fun clearSelection() {
        val selecteditems=si.toList()
        si.clear()
        selecteditems.forEach { notifyItemChanged(it) }
    }

    fun anim(view:View){
       val animation=AlphaAnimation(0.0f,1.0f)
        animation.duration=1000
        view.startAnimation(animation)
    }
    fun selectAll(){
        si.clear()
        for(i in 0 until datalist.size){
            si.add(i)
        }
        notifyDataSetChanged()
    }
}