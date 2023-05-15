package com.ayberk.deezermusicapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.deezermusicapp.*
import com.ayberk.deezermusicapp.databinding.ArtistItemBinding
import com.ayberk.deezermusicapp.models.artistX
import com.bumptech.glide.Glide

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.MyArtist>() {

    var liveData : List<artistX>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArtist {
        val binding = ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyArtist(binding)
    }


    override fun onBindViewHolder(holder: MyArtist, position: Int) {
        holder.bind(liveData!![position])

    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }else{
            return liveData!!.size
        }
    }

    inner class MyArtist(val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: artistX) {

            binding.txtname.text = data.name
            Glide.with(binding.imgArtist)
                .load(data.picture_big)
                .into(binding.imgArtist)
        }
    }
    fun setLists(liveData: List<artistX>?) {
            this.liveData = liveData
            notifyDataSetChanged()
    }
}