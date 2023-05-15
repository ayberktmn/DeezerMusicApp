package com.ayberk.deezermusicapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.deezermusicapp.HomeFragment
import com.ayberk.deezermusicapp.HomeFragmentDirections
import com.ayberk.deezermusicapp.databinding.CategoryItemBinding
import com.ayberk.deezermusicapp.models.Data
import com.bumptech.glide.Glide

class DeezerAdapter : RecyclerView.Adapter<DeezerAdapter.MyMusic>() {

    var liveData : List<Data>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMusic {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyMusic(binding)
    }


    override fun onBindViewHolder(holder: MyMusic, position: Int) {
        holder.bind(liveData!![position])
        val binding = holder.binding.gridLayout
        binding.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(liveData!![position].id)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }else{
            return liveData!!.size
        }
    }

    inner class MyMusic(internal val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {

            binding.textView.text =  data.name
            Glide.with(binding.categoryimg)
                .load(data.picture_big)
                .into(binding.categoryimg)
        }
    }
    fun setLists(liveData: List<Data>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }
}