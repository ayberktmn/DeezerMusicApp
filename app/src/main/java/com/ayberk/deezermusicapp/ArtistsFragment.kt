package com.ayberk.deezermusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.deezermusicapp.Adapter.ArtistAdapter
import com.ayberk.deezermusicapp.Adapter.ArtistAlbumAdapter
import com.ayberk.deezermusicapp.ViewModel.DeezerViewModel
import com.ayberk.deezermusicapp.databinding.FragmentArtistsBinding
import com.ayberk.deezermusicapp.models.album.Album
import com.ayberk.deezermusicapp.models.artistX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.json.JSONObject

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ArtistAdapter
    lateinit var artistAlbumadapter: ArtistAlbumAdapter
    private val viewModel: DeezerViewModel by viewModels()
    var artistId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let {

            artistId = ArtistsFragmentArgs.fromBundle(it).artistId
            println("bu artist $artistId")

        }
        val lmVertical = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcylerArtistDetails.layoutManager = lmVertical
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        InitRecycler()
        fetchCategory(artistId)

        viewModel.getArtistData().observe(viewLifecycleOwner, object : Observer<artistX> {
            override fun onChanged(t: artistX?) {

                if (t != null) {

                    adapter.setLists(arrayListOf(t))
                    println(t)
                }
            }
        })
        viewModel.getAlbumLiveData().observe(viewLifecycleOwner, object : Observer<Album> {
            override fun onChanged(t: Album?) {

                if (t != null) {
                    artistAlbumadapter.setListsAlbum(t.data)
                         println("artistttttttt${t}")



                }
                println("artist${t}")
                println(viewModel.getAlbumLiveData())
            }
        })

        return view
    }

    fun InitRecycler() {

        adapter = ArtistAdapter()
        binding.rcylerArtistDetails.adapter = adapter
        artistAlbumadapter = ArtistAlbumAdapter()
        binding.recyclerView.adapter = artistAlbumadapter
    }


    fun fetchCategory(ıdAlbums: Int) {

        CoroutineScope(Dispatchers.Main).launch {

            val job1 : Deferred<Unit> = async {

                viewModel.loadArtist(ıdAlbums)

            }
            val job2 : Deferred<Unit> = async {
                viewModel.loadAlbum(ıdAlbums)
                println("${artistId} bu artsit ıdsi")

            }

            job1.await()
            job2.await()
        }
    }
}