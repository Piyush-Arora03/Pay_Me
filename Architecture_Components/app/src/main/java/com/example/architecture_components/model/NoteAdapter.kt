package com.example.architecture_components.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_components.Dao.NoteEntity
import com.example.architecture_components.databinding.NoteLayoutBinding

class NoteAdapter(var datalist:List<NoteEntity>, var context:Context) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding:NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding=NoteLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val data=datalist.get(position)
        holder.binding.text.text=data.text
    }
}