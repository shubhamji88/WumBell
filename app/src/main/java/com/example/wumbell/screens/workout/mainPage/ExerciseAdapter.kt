package com.example.wumbell.screens.workout.mainPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wumbell.data.BodyPart
import com.example.wumbell.databinding.ExerciseListLayoutBinding


class ExerciseAdapter(val clickListner: ClickListener): ListAdapter<BodyPart, RecyclerView.ViewHolder>(BodyPartDiffCallBack()) {

    class BodyPartDiffCallBack : DiffUtil.ItemCallback<BodyPart>()
    {
        override fun areItemsTheSame(oldItem: BodyPart, newItem: BodyPart): Boolean {
            return oldItem.name==newItem.name
        }
        override fun areContentsTheSame(oldItem: BodyPart, newItem: BodyPart): Boolean {
            return oldItem==newItem
        }
    }

    class ViewHolder private constructor(val binding: ExerciseListLayoutBinding): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ExerciseListLayoutBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(view)
            }
        }
        fun bind(item: BodyPart, clickListnerimg: ClickListener) {
            binding.click=clickListnerimg
            binding.data=item
            binding.nextbutton.setOnClickListener {
                clickListnerimg.onClick(adapterPosition)
            }
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder->{
                val item=getItem(position)
                holder.bind(item,clickListner)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

}
class ClickListener(val clisklistner: (Int)-> Unit){
    fun onClick(index :Int)= clisklistner(index)
}

