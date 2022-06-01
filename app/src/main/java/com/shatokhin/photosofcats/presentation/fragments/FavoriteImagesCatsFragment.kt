package com.shatokhin.photosofcats.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.databinding.FragmentFavoriteImagesCatsBinding
import com.shatokhin.photosofcats.domain.models.CatFavorite
import com.shatokhin.photosofcats.presentation.adapters.AdapterRvFavoriteCats
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMainFactory
import com.shatokhin.photosofcats.utilites.TAG_FOR_LOGCAT


class FavoriteImagesCatsFragment : Fragment() {
    private val viewModelMain: ViewModelMain by activityViewModels { ViewModelMainFactory() }

    private var _binding: FragmentFavoriteImagesCatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterRvFavoriteCats: AdapterRvFavoriteCats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteImagesCatsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        viewModelMain.loadFavoriteCats()
    }

    private fun initRecycleView() {
        val clickListener = object : AdapterRvFavoriteCats.ClickListenerFavoriteCats{
            override fun clickFavCat(cat: CatFavorite) {
                viewModelMain.deleteFavoriteCat(cat)
            }
        }

        adapterRvFavoriteCats = AdapterRvFavoriteCats(clickListener)
        binding.rvFavoriteCats.adapter = adapterRvFavoriteCats

        val lmForRvFavoriteCats = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFavoriteCats.layoutManager = lmForRvFavoriteCats

        viewModelMain.listFavoriteCats.observe(viewLifecycleOwner){ listFavoriteCats ->
            adapterRvFavoriteCats.submitList(listFavoriteCats)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}