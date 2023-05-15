package com.ayberk.deezermusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ayberk.deezermusicapp.Adapter.FavoriteRecyclerAdapter
import com.ayberk.deezermusicapp.Room.DataDao
import com.ayberk.deezermusicapp.Room.RoomDataBase
import com.ayberk.deezermusicapp.databinding.FragmentFavoriBinding
import com.ayberk.deezermusicapp.models.album.favoriMusic

class FavoriFragment : Fragment() {


    private lateinit var musicList : List<favoriMusic>
    private lateinit var FavoriAdapter : FavoriteRecyclerAdapter
    private lateinit var db: RoomDataBase
    private lateinit var adventDao: DataDao
    private lateinit var binding: FragmentFavoriBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFavoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext().applicationContext, RoomDataBase::class.java, "Music")
            .allowMainThreadQueries()
            .build()
        adventDao = db.dataDao()

        val recyclerViewAdapter = FavoriteRecyclerAdapter(adventDao.getAll() as ArrayList<favoriMusic>)
        binding.FavoriteRecyler.adapter = recyclerViewAdapter

        binding.FavoriteRecyler.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var _binding = null
    }
}
