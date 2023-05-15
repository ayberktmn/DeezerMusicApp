package com.ayberk.deezermusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.deezermusicapp.Adapter.MusicAdapter
import com.ayberk.deezermusicapp.ViewModel.DeezerViewModel
import com.ayberk.deezermusicapp.databinding.FragmentMusicBinding

import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@AndroidEntryPoint
class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: MusicAdapter
    private val viewModel : DeezerViewModel by viewModels()
    var musicId = -1




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicBinding.inflate(inflater,container,false)
        val view = binding.root
        InitRecycler()
        fetchCategory(musicId)

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView2.layoutManager = layoutManager

        arguments?.let {

            musicId = MusicFragmentArgs.fromBundle(it).musicId



        }

        viewModel.getMusicLiveData().observe(viewLifecycleOwner, object : Observer<com.ayberk.deezermusicapp.models.album.Album> {
            override fun onChanged(t: com.ayberk.deezermusicapp.models.album.Album?) {

                if (t != null) {

                    adapter.setMusicList(t.data)

                }
            }
        })



        return view
    }

    fun InitRecycler(){

        adapter = MusicAdapter()
        binding.recyclerView2.adapter = adapter

    }
    fun fetchCategory(ıdMusic: Int){
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadMusic(musicId)
        }
    }

}