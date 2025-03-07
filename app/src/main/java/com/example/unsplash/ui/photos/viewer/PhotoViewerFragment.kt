package com.example.unsplash.ui.photos.viewer

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.unsplash.data.model.Photo
import com.example.unsplash.databinding.FragmentPhotoViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val PHOTO = "photo"

@AndroidEntryPoint
class PhotoViewerFragment : Fragment() {

    private var _binding: FragmentPhotoViewerBinding? = null
    private val binding get() = _binding!!

    private var photo: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(PHOTO, Photo::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable(PHOTO)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoViewerBinding.inflate(inflater, container, false)

        loadImage()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadImage() {

        if (photo != null) {
            Glide.with(binding.root)
                .load(photo!!.uriRaw)
                .into(binding.imageView)
        } else {
            Toast.makeText(requireContext(), "Something went wrong.", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(photo: Photo) =
            PhotoViewerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO, photo)
                }
            }
    }

}