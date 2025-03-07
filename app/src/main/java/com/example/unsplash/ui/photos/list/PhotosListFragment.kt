package com.example.unsplash.ui.photos.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.unsplash.R
import com.example.unsplash.data.model.Photo
import com.example.unsplash.databinding.FragmentPhotosListBinding
import com.example.unsplash.ui.photos.viewer.PhotoViewerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosListFragment : Fragment() {

    private var _binding: FragmentPhotosListBinding? = null
    private val binding: FragmentPhotosListBinding get() = _binding!!

    private val viewModel by viewModels<PhotosListViewModel>()

    private val rvAdapter by lazy { PhotosRvAdapter(onItemClickListener) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = rvAdapter
        viewModel.loadPhotos()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.photosLiveData.observe(viewLifecycleOwner) { list ->
            rvAdapter.updateList(list)
        }
    }

    private val onItemClickListener: (Photo) -> Unit = { photo: Photo ->  
        parentFragmentManager.beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_container_view, PhotoViewerFragment.newInstance(photo))
            .commit()

    }

}