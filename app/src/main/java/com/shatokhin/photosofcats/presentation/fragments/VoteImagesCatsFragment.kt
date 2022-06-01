package com.shatokhin.photosofcats.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.shatokhin.photosofcats.databinding.FragmentVoteImagesCatsBinding
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.photosofcats.presentation.viewmodels.ViewModelMainFactory
import com.shatokhin.photosofcats.utilites.TAG_FOR_LOGCAT


class VoteImagesCatsFragment : Fragment() {
    private val viewModelMain: ViewModelMain by activityViewModels { ViewModelMainFactory() }

    private var _binding: FragmentVoteImagesCatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVoteImagesCatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain.loadRandomCats()

        viewModelMain.listCats.observe(viewLifecycleOwner){ newRandomCat ->
            setImage( newRandomCat.urlImage )
        }

        binding.btnVoteUp.setOnClickListener {
            viewModelMain.voteUpCurrentCat()
        }

        binding.btnVoteDown.setOnClickListener {
            viewModelMain.voteDownCurrentCat()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setImage(urlImage: String){
        val imageUri = Uri.parse(urlImage)

        val imageRequest= ImageRequestBuilder
            .newBuilderWithSource(imageUri)
            .setProgressiveRenderingEnabled(true)
            .build()

        binding.ivImageCat.setImageRequest(imageRequest)
    }

}