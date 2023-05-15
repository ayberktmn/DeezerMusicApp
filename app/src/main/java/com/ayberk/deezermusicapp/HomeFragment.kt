package com.ayberk.deezermusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.deezermusicapp.Adapter.DeezerAdapter
import com.ayberk.deezermusicapp.ViewModel.DeezerViewModel
import com.ayberk.deezermusicapp.databinding.FragmentHomeBinding
import com.ayberk.deezermusicapp.models.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: DeezerAdapter
    private val viewModel : DeezerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.rcylerCategory.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)

        InitRecycler()
        fetchCategory()

        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<Category> {
            override fun onChanged(t: Category?) {
                if(t != null){
                    adapter.setLists(t.data)
                }
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun InitRecycler(){

        adapter = DeezerAdapter()
        binding.rcylerCategory.adapter = adapter

    }
    fun fetchCategory(){
        CoroutineScope(Dispatchers.Main).async {
            viewModel.loadCategory()
        }
    }
}