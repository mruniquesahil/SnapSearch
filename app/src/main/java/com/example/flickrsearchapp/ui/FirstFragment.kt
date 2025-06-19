package com.example.flickrsearchapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrsearchapp.Model.PhotosResponse
import com.example.flickrsearchapp.R
import com.example.flickrsearchapp.databinding.FragmentFirstBinding
import com.example.flickrsearchapp.network.PhotosRepository
import com.example.flickrsearchapp.network.RetrofitService
import com.example.flickrsearchapp.viewModel.FlickrViewModel
import com.example.flickrsearchapp.viewModel.FlickrViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), PhotoAdapter.OnImageClickListener {

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: FlickrViewModel
    private val retrofitService = RetrofitService.getInstance()
    private var adapter = PhotoAdapter(emptyList(), this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().run {
            ViewModelProvider(
                this,
                FlickrViewModelFactory(PhotosRepository(retrofitService))
            ).get(FlickrViewModel::class.java)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getPhotos(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.getPhotos(newText)
                return false
            }
        })

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.photoList.layoutManager = layoutManager
        setObserver()
    }

    private fun setObserver() {
        viewModel.list.observe(viewLifecycleOwner, Observer<PhotosResponse> {
            if (it.photos != null) {
                adapter = PhotoAdapter(it.photos.photo, this)
                binding.photoList.adapter = adapter
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onImageClick(url: String) {
        viewModel.imageUrl.value = url
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}